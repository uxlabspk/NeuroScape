package io.github.uxlabspk.neuroscape.views


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.github.uxlabspk.neuroscape.data.Questions
import io.github.uxlabspk.neuroscape.data.ScanReports
import io.github.uxlabspk.neuroscape.ml.Model
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

@Composable
fun QuestionsScreen(
    navController: NavController
) {
    val context = LocalContext.current

    val questions = listOf(
        Questions(id = 0, text = "Does your child look at you when you call his/her name?"),
        Questions(id = 1, text = "How easy is it for you to get eye contact with your child?"),
        Questions(id = 2, text = "Does your child point to indicate that s/he wants something? (e.g. a toy that is out of reach)"),
        Questions(id = 3, text = "Does your child point to share interest with you? (e.g. pointing at an interesting sight)"),
        Questions(id = 4, text = "Does your child pretend? (e.g. care for dolls, talk on a toy phone)"),
        Questions(id = 5, text = "Does your child follow where you’re looking?"),
        Questions(id = 6, text = "If you or someone else in the family is visibly upset, does your child show signs of wanting to comfort them? (e.g. stroking hair, hugging them)"),
        Questions(id = 7, text = "Would you describe your child’s first words as : "),
        Questions(id = 8, text = "Does your child use simple gestures? (e.g. wave goodbye)"),
        Questions(id = 9, text = "Does your child stare at nothing with no apparent purpose?"),
    )

    var selectedAnswers by remember { mutableStateOf(List(questions.size) { -1 }) }

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(text = "Scan Questions", modifier = Modifier) {
            navController.navigateUp()
        }
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
                .fillMaxSize(9 / 10f)
        ) {

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                itemsIndexed(questions) { index, question ->
                    QuestionItem(
                        question = question,
                        selectedAnswer = selectedAnswers[index]
                    ) { answer ->
                        selectedAnswers = selectedAnswers.toMutableList().also {
                            it[index] = answer
                        }
                    }
                }
            }
        }

        Row(
            Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryButton(
                text = "Generate",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                if (isValidArray(selectedAnswers)) {
                    val mappedList = selectedAnswers.mapIndexed { index, value ->
                        when {
                            index < 9 -> if (value in 2..4) 1 else 0
                            else -> if (value in 0..2) 1 else 0
                        }
                    }
                    val mappedArray = mappedList.map { it.toFloat() }.toFloatArray()
                    val result = modelRead(context, mappedArray)
                    val arg = result == 1

                    updateDatabase(mappedList.sum())

//                    navController.addOnDestinationChangedListener { controller, destination, _ ->
//                        if (destination.route == "questionsScreen") {
//                            controller.navigate("result/$arg")
//                        }
//                    }
                    navController.navigate("result/$arg")
                } else {
                    Toast.makeText(context, "Please fill all questions before proceeding", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

private fun updateDatabase(sum: Int) {
    val databaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("Reports")

    databaseRef.limitToLast(1).get().addOnSuccessListener {datasnapshot ->
        for (childSnapshot in datasnapshot.children) {
            val scanReport = childSnapshot.getValue(ScanReports::class.java)
            scanReport?.reportResult = sum.toString()

            databaseRef.child(childSnapshot.key.toString()).setValue(scanReport)
        }
    }
}

@Composable
fun QuestionItem(
    question: Questions,
    selectedAnswer: Int,
    onAnswerSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = "Q${question.id + 1}. ${question.text}",
            modifier = Modifier.padding(bottom = 8.dp),
            fontFamily = SF_Font_Family,
            fontWeight = FontWeight.Medium
        )
        question.options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = index == selectedAnswer,
                    onClick = { onAnswerSelected(index) },
                    colors = RadioButtonDefaults.colors(BlueColor),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = option,
                    modifier = Modifier,
                    fontFamily = SF_Font_Family
                )
            }
        }
    }
}

private fun isValidArray(array: List<Int>): Boolean {
    return array.all { it != -1 && it <= 4 }
}

fun modelRead(context: Context, questionAnswers: FloatArray): Int {
    val model = Model.newInstance(context)

    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 10), DataType.FLOAT32)
    inputFeature0.loadArray(questionAnswers)

    val outputs = model.process(inputFeature0)

    val outputFeature0 = outputs.outputFeature0AsTensorBuffer

    val intArray: IntArray = outputFeature0.floatArray.map { it.toInt() }.toIntArray()

    model.close()

    return intArray[0]
}
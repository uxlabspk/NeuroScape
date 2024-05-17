package io.github.uxlabspk.neuroscape.views


import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.uxlabspk.neuroscape.data.Questions
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar

@Composable
fun QuestionsScreen() {
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
            // navigation
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
                    Log.d("Size", "QuestionsScreen: $selectedAnswers")
                } else {
                    Toast.makeText(context, "Please fill all questions before proceeding", Toast.LENGTH_LONG).show()
                }
            }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    QuestionsScreen()
}


//
//@Composable
//fun QuestionsScreen(
//    navController: NavController,
//) {
//    var index by remember { mutableStateOf(0) }
//    val context = LocalContext.current
//
//    var questionsList = listOf(
//        "Does your child look at you when you call his/her name?",
//        "How easy is it for you to get eye contact with your child?",
//        "Does your child point to indicate that s/he wants something? (e.g. a toy that is out of reach)",
//        "Does your child point to share interest with you? (e.g. pointing at an interesting sight)",
//        "Does your child pretend? (e.g. care for dolls, talk on a toy phone)",
//        "Does your child follow where you’re looking?",
//        "If you or someone else in the family is visibly upset, does your child show signs of wanting to comfort them? (e.g. stroking hair, hugging them)",
//        "Would you describe your child’s first words as:",
//        "Does your child use simple gestures? (e.g. wave goodbye)",
//        "Does your child stare at nothing with no apparent purpose?",
//    )
//
//    val back = { index = (index - 1 + questionsList.size) % questionsList.size }
//    var next = if (index == 9) {
//        {
//            navController.navigate("result")
//            modelRead(context)
//        }
//    } else {
//        { index = (index + 1) % questionsList.size }
//    }
//
//    //if (index < 9) reset = true
//
//
//    MainScreen(navController, questionsList[index], index, next, back)
//}
//
//
//@Composable
//fun MainScreen(
//    navController: NavController,
//    questions: String,
//    questionsIndex: Int,
//    onNextClicked: () -> Unit,
//    onBackClicked: () -> Unit
//) {
//    val nextText = if (questionsIndex == 9) "Generate Report" else "Next"
//
//    // val btnOnClick = if (next_Text == "Next") {onNextClicked} else { navController.navigate("home") }
//
//    var answersList = mutableMapOf<String, String>()
//
////        answerList.put("key2", "value2")
//
//    Column(
//        Modifier.background(MaterialTheme.colorScheme.background)
//    ) {
//        TopBar(text = "${questionsIndex + 1}/10", modifier = Modifier) {
//            navController.navigateUp()
//        }
//        Column(
//            Modifier
//                .padding(horizontal = 20.dp)
//                .padding(top = 20.dp)
//        ) {
//            Box(
//                Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(MaterialTheme.colorScheme.surface)
//            ) {
//                Text(text = questions, Modifier.padding(10.dp))
//            }
//
//            Column(
//                Modifier
//                    .fillMaxHeight(8 / 9f)
//                    .padding(top = 10.dp)
//            ) {
//                McqsRadioButton(
//                    option1 = "Always",
//                    option2 = "Usually",
//                    option3 = "Sometimes",
//                    option4 = "Rarely",
//                    option5 = "Never"
//                )
//            }
//
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 15.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//
//                NavigationButton(
//                    text = "Previous",
//                    modifier = Modifier.width(150.dp),
//                    isNext = false,
//                    onClick = onBackClicked
//                )
//
//                NavigationButton(
//                    text = nextText,
//                    modifier = Modifier.width(150.dp),
//                    isNext = true,
//                    onClick = onNextClicked
//                )
//            }
//        }
//    }
//}
//
//fun modelRead(context: Context) {
//    val model = Model.newInstance(context)
//
//    // Creates inputs for reference.
//    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 10), DataType.FLOAT32)
//
//    inputFeature0.loadArray(floatArrayOf(0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F))
//
//    // Runs model inference and gets result.
//    val outputs = model.process(inputFeature0)
//    val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//    val floatArray: FloatArray = outputFeature0.floatArray
//    val intArray: IntArray = floatArray.map { it.toInt() }.toIntArray()
//
//
//    // Showing the output to logcat
//    Log.d("Output", intArray[0].toString())
//
//    model.close()
//}


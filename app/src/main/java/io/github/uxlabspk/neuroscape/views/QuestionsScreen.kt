package io.github.uxlabspk.neuroscape.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.uxlabspk.neuroscape.ml.Model
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.views.components.McqsRadioButton
import io.github.uxlabspk.neuroscape.views.components.NavigationButton
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.util.Arrays



@Composable
fun QuestionsScreen() {
    var questionsList = listOf(
        "Does your child look at you when you call his/her name?",
        "How easy is it for you to get eye contact with your child?",
        "Does your child point to indicate that s/he wants something? (e.g. a toy that is out of reach)",
        "Does your child point to share interest with you? (e.g. pointing at an interesting sight)",
        "Does your child pretend? (e.g. care for dolls, talk on a toy phone)",
        "Does your child follow where you’re looking?",
        "If you or someone else in the family is visibly upset, does your child show signs of wanting to comfort them? (e.g. stroking hair, hugging them)",
        "Would you describe your child’s first words as:",
        "Does your child use simple gestures? (e.g. wave goodbye)",
        "Does your child stare at nothing with no apparent purpose?",
    )

    Column(
        Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(text = "Scan Questions", modifier = Modifier) {
            // navController.navigateUp()
        }
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
                .fillMaxHeight(9 / 10f)
        ) {
            LazyColumn {
                items(10) {
                    Questions(title = questionsList[it])
                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            PrimaryButton(text = "Generate", modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {

            }
        }
    }
    
}

@Composable
fun Questions(title: String) {
    Column {
        Text(title)
        McqsRadioButton(
                    option1 = "Always",
                    option2 = "Usually",
                    option3 = "Sometimes",
                    option4 = "Rarely",
                    option5 = "Never"
                )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewQS() {
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


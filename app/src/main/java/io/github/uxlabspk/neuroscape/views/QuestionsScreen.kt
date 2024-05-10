package io.github.uxlabspk.neuroscape.views

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.views.components.McqsRadioButton
import io.github.uxlabspk.neuroscape.views.components.NavigationButton
import io.github.uxlabspk.neuroscape.views.components.TopBar

// FirebaseDatabase.getInstance().setPersistenceEnabled(true)

@Composable
fun QuestionsScreen(
    navController: NavController,
) {
    var index by remember { mutableStateOf(0) }
    val contents = listOf(
        "Content 1",
        "Content 2",
        "Content 3",
        "Content 4",
        "Content 5",
        "Content 6",
        "Content 7",
        "Content 8",
        "Content 9",
        "Content 10",
    )

    val back = { index = (index - 1 + contents.size) % contents.size }
    val next = { index = (index + 1) % contents.size }

    MainScreen(navController, contents[index], index, next, back)
}


@Composable
fun MainScreen(
    navController: NavController, questions: String, questionsIndex: Int, onNextClicked: () -> Unit, onBackClicked: () -> Unit
) {
    val nextText = if (questionsIndex == 9) "Generate Report" else "Next"

    // val btnOnClick = if (next_Text == "Next") {onNextClicked} else { navController.navigate("home") }
    var questionsList: List<String> = listOf(
        "sdf",
        "SDf"
    )

    Column(
        Modifier.background(Color.White)
    ) {
        TopBar(text = "${questionsIndex + 1}/10", modifier = Modifier) {
            navController.navigateUp()
        }
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        ) {
            Box (
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(OffWhiteColor)
            ) {
                Text(text = questions, Modifier.padding(10.dp))
            }

            Column (
                Modifier
                    .fillMaxHeight(8 / 9f)
                    .padding(top = 10.dp)
            ) {
                McqsRadioButton(option1 = "Hamza", option2 = "Naveed", option3 = "CEO", option4 = "CTO", option5 = "Killer")
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                NavigationButton(
                    text = "Previous",
                    modifier = Modifier.width(150.dp),
                    isNext = false,
                    onClick = onBackClicked
                )

                NavigationButton(
                    text = nextText,
                    modifier = Modifier.width(150.dp),
                    isNext = true,
                    onClick = onNextClicked
                )
            }
        }
    }
}




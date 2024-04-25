package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.views.components.McqsRadioButton
import io.github.uxlabspk.neuroscape.views.components.NavigationButton
import io.github.uxlabspk.neuroscape.views.components.TopBar

@Composable
fun QuestionsScreen() {
    Column(
        Modifier.background(Color.White)
    ) {
        TopBar(text = "01/10", modifier = Modifier, {})
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        ) {
            Card(
                Modifier.fillMaxWidth()
            ) {
                Text(text = "sdsdf", Modifier.padding(10.dp))
            }



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .padding(vertical = 16.dp)
                    .background(BlueColor)
            )

            Row {

                NavigationButton(
                    text = "Previous",
                    modifier = Modifier.fillMaxWidth(1 / 2f),
                    isNext = false,
                    {})
                NavigationButton(
                    text = "Next",
                    modifier = Modifier.fillMaxWidth(),
                    isNext = true,
                    {})

            }




            Column {
                McqsRadioButton(option1 = "Hamza", option2 = "Naveed", option3 = "CEO", option4 = "CTO")

            }

        }
    }
}

@Preview(showBackground = true, widthDp = 330, heightDp = 800)
@Composable
fun PreviewQuestionsScreen() {
    QuestionsScreen()
}
package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor

@Composable
fun McqsRadioButton(
    option1: String,
    option2: String,
    option3: String,
    option4: String,
    option5: String
) {
    val optionsMcqs = listOf(option1, option2, option3, option4, option5)
    var optionState by remember { mutableStateOf("") }

    Box (
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),

        ) {
            optionsMcqs.forEach {
                CustomRadioGroup(
                    selected = optionState == it,
                    title = it) { data->
                    optionState = data

                }
            }

        }
    }
}

@Composable
fun CustomRadioGroup(
    selected: Boolean,
    title: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 4.dp,
            contentColor = Color.Black,
            color = Color.LightGray,
            border = BorderStroke(
                width = 2.dp,
                color = if (selected) BlueColor else Color.Transparent
            )
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title ,
                    modifier = Modifier.padding(horizontal = 12.dp))
                RadioButton(
                    selected = selected,
                    colors =  RadioButtonDefaults.colors(BlueColor),
                    onClick = {
                    onValueChange(title)

                })
            }

        }

    }
}

@Preview
@Composable
fun McqsPreview() {
    McqsRadioButton(option1 = "Hamza Waheed", option2 = "Naveed", option3 = "CEO", option4 = "CTO", option5 = "sdf")

}


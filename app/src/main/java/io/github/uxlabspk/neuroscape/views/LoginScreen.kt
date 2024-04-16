package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.uxlabspk.neuroscape.views.components.TopBar


@Composable
fun LoginScreen() {
    Column {
        TopBar(text = "", modifier = Modifier)
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,

                ),
                placeholder = {
                    Text(text = "sdfsdf")
                },
                shape = RoundedCornerShape(8.dp)

            )
        }
    }
}


@Preview(showBackground = true, widthDp = 330, heightDp = 800)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.uxlabspk.neuroscape.views.components.TopBar
import io.github.uxlabspk.neuroscape.views.components.UserInput


@Composable
fun LoginScreen() {
    Column {
        TopBar(text = "", modifier = Modifier)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UserInput(text = "Username", inputIcon = Icons.Default.Email)
            UserInput(text = "Username", inputIcon = Icons.Default.AccountBox)
        }
    }
}


@Preview(showBackground = true, widthDp = 330, heightDp = 800)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
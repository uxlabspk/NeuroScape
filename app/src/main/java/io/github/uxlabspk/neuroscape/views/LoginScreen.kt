package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.views.components.PasswordInput
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import io.github.uxlabspk.neuroscape.views.components.UserInput


@Composable
fun LoginScreen() {
    Column(Modifier.background(Color.White)) {
        TopBar(text = "Login", modifier = Modifier.height(54.dp), {})
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.width(200.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null
            )
            UserInput(
                text = "Enter your email...", inputIcon = Icons.Default.Email,
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            PasswordInput(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            PrimaryButton(text = "Login", modifier = Modifier.fillMaxWidth(), {})
            Row(
                modifier = Modifier.height(34.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Don't have an account? ")
                TextButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text("Create Account")
                }
            }

        }
    }
}


@Preview(showBackground = true, widthDp = 330, heightDp = 800)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
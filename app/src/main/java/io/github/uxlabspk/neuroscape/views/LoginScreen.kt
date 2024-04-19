package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
    Column {
        TopBar(text = "Login", modifier = Modifier.height(54.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_sign_in_flow),
                contentDescription = null
            )
            UserInput(
                text = "Enter your username...", inputIcon = Icons.Default.Email,
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            PasswordInput(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            PrimaryButton(text = "Login", modifier = Modifier.fillMaxWidth())
        }
    }
}


@Preview(showBackground = true, widthDp = 330, heightDp = 800)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
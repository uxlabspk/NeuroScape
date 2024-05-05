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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.views.components.PasswordInput
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import io.github.uxlabspk.neuroscape.views.components.UserInput

@Composable
fun NewScanScreen(
    navController: NavController,
) {
    Column(Modifier.background(Color.White)) {
        TopBar(text = "New Scan", modifier = Modifier.height(54.dp), {})
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            UserInput(
                text = "Enter report name...", inputIcon = Icons.Default.Star,
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                isEnabled = true
            )
            PrimaryButton(text = "Start", modifier = Modifier.fillMaxWidth(), {})

        }
    }
}


package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.SecondaryButton

@Composable
fun IntroScreen(navController: NavController, heading: String, subheading: String) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                heading,
                fontSize = 33.sp,
                fontFamily = SF_Font_Family,
                fontWeight = FontWeight.Medium
            )
            Text(
                subheading,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(12.dp),
                fontFamily = SF_Font_Family,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            PrimaryButton(
                "Login",
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(vertical = 2.dp)
            ) {
                navController.navigate(
                    "signin"
                )
            }
            SecondaryButton(
                "Signup",
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(vertical = 2.dp)
            ) {
                navController.navigate(
                    "signup"
                )
            }
        }
    }
}

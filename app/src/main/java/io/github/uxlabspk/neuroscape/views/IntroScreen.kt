package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.SecondaryButton

@Composable
fun IntroScreen(heading: String, subheading: String) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                heading,
                fontSize = 33.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                subheading,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(12.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            PrimaryButton("Login", Modifier.fillMaxWidth().padding(horizontal = 32.dp).padding(vertical = 2.dp))
            SecondaryButton("Signup", Modifier.fillMaxWidth().padding(horizontal = 32.dp).padding(vertical = 2.dp))
        }
    }
}


@Preview(showBackground = true, widthDp = 330, heightDp = 800)
@Composable
fun IntroScreenPreview() {
    IntroScreen("Autism", "Autism specturnm disorder (ASD) is underdevelopement condition characterized by challenges in social interaction, communication, and behaviour.")
}
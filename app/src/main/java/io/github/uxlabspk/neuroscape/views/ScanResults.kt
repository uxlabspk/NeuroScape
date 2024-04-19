package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar

@Composable
fun ScanResults(isAutismTraits: Boolean) {
    var icon:String
    var heading: String
    var subheading:String

    if (isAutismTraits) {
        icon = "\uD83D\uDE22"
        heading = "Alas"
        subheading =  "Autism traits are found in this scan."
    } else {
        icon = "\uD83D\uDE0D\uFE0F"
        heading = "Hurrah"
        subheading =  "No Autism traits are found in this scan."
    }


    Column(
        modifier = Modifier.background(Color.White)
    ) {
        TopBar(text = "Sign in", modifier = Modifier
            .fillMaxWidth()
            .height(44.dp))

        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                icon,
                fontSize = 96.sp
            )
            Text(
                heading,
                fontSize = 40.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = subheading,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            PrimaryButton(text = "Go Home", modifier = Modifier.padding(top = 120.dp))
        }
    }

}


@Preview(showBackground = true, widthDp = 330, heightDp = 800)
@Composable
fun ScanResultsPreview() {
    ScanResults(false)
}
package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.uxlabspk.neuroscape.R


@Composable
fun TopBar(text: String, modifier: Modifier) {
    Surface(
        modifier = modifier,
        shadowElevation = 3.dp
    ) {
        Row(
            modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(Icons.Default.KeyboardArrowLeft, contentDescription = null)
            Text(
                text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(end = 30.dp)
            )
        }
    }
}




@Preview (widthDp = 300, heightDp = 42)
@Composable
fun PreviewTopBar() {
    TopBar(text = "sdf", Modifier.fillMaxWidth())
}
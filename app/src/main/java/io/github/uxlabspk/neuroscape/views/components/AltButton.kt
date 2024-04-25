package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.uxlabspk.neuroscape.ui.theme.LightTextColor
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor

@Composable
fun AltButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = OffWhiteColor,
            contentColor = LightTextColor
        ),
        shape = RoundedCornerShape(5.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp
        )
    ) {
        Text(text)
    }
}



@Preview
@Composable
fun AltButtonPreview() {
    AltButton("View All Scans", Modifier, {})
}
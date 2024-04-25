package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.uxlabspk.neuroscape.ui.theme.BlackColor
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.GrayColor


@Composable
fun NavigationButton(
    text: String,
    modifier: Modifier,
    isNext: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isNext) BlueColor else GrayColor
    val textColor = if (isNext) Color.White else BlackColor

    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = Color(0xFFffffff)
        ),
        shape = CircleShape,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp
        )
    ) {
        Text(text, color = textColor)
    }
}



@Preview(widthDp = 300, heightDp = 42)
@Composable
fun PreviewNavigationButton() {
    NavigationButton(text = "sdf", Modifier, false ,{})
}
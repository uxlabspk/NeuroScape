package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.uxlabspk.neuroscape.ui.theme.BlackColor
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.GrayColor
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.ui.theme.WhiteColor


@Composable
fun NavigationButton(
    text: String,
    modifier: Modifier,
    isNext: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isNext) BlueColor else GrayColor
    val textColor = if (isNext) WhiteColor else OffWhiteColor

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
        Text(text, color = textColor,  fontFamily = SF_Font_Family, fontWeight = FontWeight.Medium)
    }
}


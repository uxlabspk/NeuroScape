package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun TextFieldFun(
    text: String
) {
    var textState by remember {
        mutableStateOf("")
    }

    TextField(
        value = textState,
        onValueChange = { textState = it},
        modifier = Modifier,
        label = { Text(text) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.DarkGray,
            focusedLabelColor = Color.DarkGray,
            focusedLeadingIconColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.DarkGray,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.LightGray,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(25.dp),
        singleLine = true,
    )
}


@Preview
@Composable
fun PreviewTextField () {
    TextFieldFun(text = "Username")
}

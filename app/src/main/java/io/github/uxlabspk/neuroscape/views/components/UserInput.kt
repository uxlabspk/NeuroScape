package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import io.github.uxlabspk.neuroscape.R


@Composable
fun UserInput(
    text: String,
    inputIcon: ImageVector,
    modifier: Modifier,
    isEnabled: Boolean
) {
    var textState by remember {
        mutableStateOf("")
    }

    TextField(
        enabled = isEnabled,
        value = textState,
        onValueChange = { textState = it},
        modifier = modifier,
        label = { Text(text) },
        leadingIcon = {
            Icon(imageVector = inputIcon, contentDescription = "Email Icon")
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
        shape = RoundedCornerShape(5.dp),
        singleLine = true,

    )
}

@Composable
fun PasswordInput(modifier: Modifier) {
    var password by rememberSaveable {
        mutableStateOf("")
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    var icon =
        if(passwordVisibility) painterResource(id = R.drawable.ic_visible)
        else painterResource(id = R.drawable.ic_invisible)

    TextField(
        modifier = modifier,
        value = password,
        onValueChange = {
            password = it
        },
        label = { Text(text = "Password")},
        leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.ic_password), contentDescription = "Password Icon")
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(
                    painter = icon,
                    contentDescription = "visibility icon"
                )
            }
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
        shape = RoundedCornerShape(5.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if(passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Preview
@Composable
fun PasswordInputPreview() {
    PasswordInput(Modifier)
}

@Preview
@Composable
fun PreviewTextField () {
    UserInput(text = "Username", inputIcon = Icons.Default.Email, Modifier, isEnabled = true)

}

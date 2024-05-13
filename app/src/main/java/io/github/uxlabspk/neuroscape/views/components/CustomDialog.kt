package io.github.uxlabspk.neuroscape.views.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.uxlabspk.neuroscape.ui.theme.GrayColor
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family


@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        containerColor = Color.White,

        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(dialogTitle, fontFamily = SF_Font_Family, fontWeight = FontWeight.SemiBold)
        },
        text = {
            Text(
                dialogText,
                fontFamily = SF_Font_Family,
                fontWeight = FontWeight.Medium,
                color = GrayColor
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            PrimaryButton(text = "Confirm", modifier = Modifier) {
                onConfirmation()
            }
        },
        dismissButton = {
            AltButton(text = "Dismiss", modifier = Modifier) {
                onDismissRequest()
            }
        }
    )
}

@Preview
@Composable
fun Preview() {
    CustomDialog(
        onDismissRequest = { /*TODO*/ },
        onConfirmation = { /*TODO*/ },
        dialogTitle = "Do you want to delete?",
        dialogText = "Choose any option",
        icon = Icons.Default.Face
    )
}
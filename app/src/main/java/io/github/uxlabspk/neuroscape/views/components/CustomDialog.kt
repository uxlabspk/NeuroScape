package io.github.uxlabspk.neuroscape.views.components


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family


@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.surface,

        title = {
            Text(dialogTitle, fontFamily = SF_Font_Family, fontWeight = FontWeight.SemiBold)
        },
        text = {
            Text(
                dialogText,
                fontFamily = SF_Font_Family,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimary
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
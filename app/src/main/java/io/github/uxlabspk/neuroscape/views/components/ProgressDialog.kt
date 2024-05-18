package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressDialog(
    isLoading: Boolean, title: String
) {
    if (isLoading) {
        Dialog(onDismissRequest = { }) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(300.dp)
                    .height(150.dp)
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = BlueColor
                    )
                    Text(modifier = Modifier.padding(top = 20.dp), text = title, fontFamily = SF_Font_Family, fontWeight = FontWeight.Normal)
                }
            }
        }
    }
}



@Preview(showBackground = true) 
@Composable
fun PreviewProgressDialog() {
    ProgressDialog(true, "Authenticating")
}
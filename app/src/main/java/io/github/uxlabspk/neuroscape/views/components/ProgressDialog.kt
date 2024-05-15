package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressDialog(
    progressValue: String
) {
    AlertDialog(
        onDismissRequest = {},
        Modifier
            .background(MaterialTheme.colorScheme.surface)
            .clip(RoundedCornerShape(18.dp))
            .padding(20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            CircularProgressIndicator(
                color = BlueColor,
                modifier = Modifier.size(36.dp),
                progress = 80f,
            )

            Text(text = progressValue, Modifier.padding(horizontal = 10.dp), fontFamily = SF_Font_Family, fontWeight = FontWeight.Medium, fontSize = 18.sp)
        }
    }

}



@Preview(showBackground = true) 
@Composable
fun PreviewProgressDialog() {
    ProgressDialog("Loading ...")
}
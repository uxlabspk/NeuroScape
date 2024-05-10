package io.github.uxlabspk.neuroscape.views.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.ui.theme.RedColor
import java.util.Locale


@Composable
fun UserInfo(modifier: Modifier, username: String, time: String, onClick: () -> Unit) {
    Surface(
        onClick,
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth(),
    ) {
        Row(
            modifier = modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .height(60.dp)
                    .padding(end = 10.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null
            )
            Column {
                Text("Hi, ${username}!", fontWeight = FontWeight.SemiBold)
                Text("Last Scan at ${time}")
            }
        }
    }
}

@Composable
fun RecentScans(
    scanTitle: String,
    scanScore: String,
    modifier: Modifier
) {
    val isAutismTrait = scanScore > "3"

    val color = if (isAutismTrait) RedColor else Color.Green
    val text = if (isAutismTrait) "ASD" else "No ASD"
    val textColor = if (isAutismTrait) Color.White else Color.Black

    Surface (
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(scanTitle, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text("Scan Results ${scanScore}/10")
            }
            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(5.dp),
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text, color = textColor)
            }
        }
    }
}





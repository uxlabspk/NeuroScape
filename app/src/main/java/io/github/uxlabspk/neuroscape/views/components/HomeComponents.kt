package io.github.uxlabspk.neuroscape.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.ui.theme.RedColor
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family


@Composable
fun UserInfo(modifier: Modifier, bitmapImg: Painter?, username: String, time: String, onClick: () -> Unit) {
    Surface(
        onClick,
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth().background(MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .height(60.dp)
                    .width(60.dp),
                painter = bitmapImg ?: painterResource(id = R.drawable.ic_account),
                // painter =  bitmapImg?.let { BitmapPainter(it) } ?: painterResource(id = R.drawable.ic_account),
                contentDescription = "Profile photo"
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text("Hi, ${username}!", fontFamily = SF_Font_Family, fontWeight = FontWeight.SemiBold)
                Text("Last Scan at $time", fontFamily = SF_Font_Family, fontWeight = FontWeight.Normal, fontSize = 14.sp)
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

    val color = if (isAutismTrait) RedColor else BlueColor
    val text = if (isAutismTrait) "ASD" else "No ASD"
    val textColor = Color.White

    Surface (
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(vertical = 4.dp)
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()

    ) {
        Row(
            modifier = modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(scanTitle, fontSize = 16.sp, fontFamily = SF_Font_Family, fontWeight = FontWeight.SemiBold)
                Text("Scan Results ${scanScore}/10", fontFamily = SF_Font_Family, fontWeight = FontWeight.Normal)
            }
            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(5.dp),
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text, color = textColor, fontFamily = SF_Font_Family, fontWeight = FontWeight.Medium, fontSize = 12.sp)
            }
        }
    }
}





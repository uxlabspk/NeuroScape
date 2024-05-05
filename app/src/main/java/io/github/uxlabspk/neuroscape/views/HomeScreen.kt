package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.views.components.AltButton
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.RecentScans
import io.github.uxlabspk.neuroscape.views.components.TopBar
import io.github.uxlabspk.neuroscape.views.components.UserInfo

@Composable
fun HomeScreen(
    navController: NavController,
) {
    Column(
        Modifier.background(Color.White)
    ) {
        TopBar(text = "Home", modifier = Modifier.height(54.dp), {})
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        ) {
            UserInfo(Modifier.background(OffWhiteColor), "prog_naveed", "April 20, 1999")
            Row(
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AltButton("All Scans", Modifier, {})
                PrimaryButton("New Scans", Modifier, {})
            }
            Text("Resents", Modifier.padding(vertical = 15.dp), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            LazyColumn {
                items(4) { item ->
                    RecentScans("sdf", 3, true, Modifier.background(OffWhiteColor))
                }
            }

        }
    }
}


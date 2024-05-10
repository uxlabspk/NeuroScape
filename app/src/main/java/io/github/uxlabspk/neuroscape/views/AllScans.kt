package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.views.components.RecentScans
import io.github.uxlabspk.neuroscape.views.components.TopBar

@Composable
fun AllScans(
    navController: NavController
) {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()) {
        TopBar(text = "All Scans", modifier = Modifier.height(54.dp)) {
            navController.navigateUp()
        }
        Column(
            Modifier
                .padding(20.dp, 10.dp)
        ) {
            LazyColumn {
                items(12) {item ->
                    RecentScans("Testing", "3", Modifier.background(OffWhiteColor))
                }
            }
        }
    }
}
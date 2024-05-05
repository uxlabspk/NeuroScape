package io.github.uxlabspk.neuroscape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import io.github.uxlabspk.neuroscape.ui.theme.NeuroScapeTheme
import io.github.uxlabspk.neuroscape.views.QuestionsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeuroScapeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}


//NavHost(navController = navController, startDestination = "screen1") {
//    composable("screen1") { Screen1(navController) }
//    composable("screen2") { Screen2(navController) }
//    // Define more destinations as needed
//}
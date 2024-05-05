package io.github.uxlabspk.neuroscape

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import io.github.uxlabspk.neuroscape.views.IntroScreen
import io.github.uxlabspk.neuroscape.views.LoginScreen


/*
* 1. Welcome
* 2. signin
* 3. signup
* 4. home
* 5. newscan
* 6. questionsScreen
* 7. results
* 8. recent scans
* 9. profile
*/


@Composable
fun NeuroScapeNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "Welcome") {
        composable("Welcome") {
            IntroScreen(navController, "Autism", "Autism specturnm disorder (ASD) is underdevelopement condition characterized by challenges in social interaction, communication, and behaviour.")
        }
        composable("signin") {
            LoginScreen(navController = navController)
        }

    }
}
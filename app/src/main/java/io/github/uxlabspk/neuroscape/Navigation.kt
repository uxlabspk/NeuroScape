package io.github.uxlabspk.neuroscape

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import io.github.uxlabspk.neuroscape.views.HomeScreen
import io.github.uxlabspk.neuroscape.views.IntroScreen
import io.github.uxlabspk.neuroscape.views.LoginScreen
import io.github.uxlabspk.neuroscape.views.NewScanScreen
import io.github.uxlabspk.neuroscape.views.ProfileScreen
import io.github.uxlabspk.neuroscape.views.QuestionsScreen
import io.github.uxlabspk.neuroscape.views.ScanResults
import io.github.uxlabspk.neuroscape.views.SignupScreen


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
        composable("signup") {
            SignupScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("newscan") {
            NewScanScreen(navController = navController)
        }
        composable("questionsScreen") {
            QuestionsScreen(navController = navController)
        }
        composable("result") {
            ScanResults(navController = navController, isAutismTraits = false)
        }
        composable("profile") {
            ProfileScreen(navController = navController, userName = "Hamza", userEmail = "codehuntspk@gmail.com")
        }


    }
}
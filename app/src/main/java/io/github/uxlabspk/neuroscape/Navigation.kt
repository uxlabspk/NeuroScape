package io.github.uxlabspk.neuroscape

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import io.github.uxlabspk.neuroscape.views.AllScans
import io.github.uxlabspk.neuroscape.views.EditProfile
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
    val startScreen = if (FirebaseAuth.getInstance().currentUser != null) "home" else "Welcome"

    NavHost(navController = navController, startDestination = startScreen) {
        composable("Welcome") {
            IntroScreen(navController, "Autism", "Autism spectrum disorder (ASD) is underdevelopment condition characterized by challenges in social interaction, communication, and behaviour.")
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
        composable("allscans") {
            AllScans(navController = navController)
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
            ProfileScreen(navController = navController)
        }
        composable("editprofile") {
            EditProfile() //(navController = navController)
        }
    }
}
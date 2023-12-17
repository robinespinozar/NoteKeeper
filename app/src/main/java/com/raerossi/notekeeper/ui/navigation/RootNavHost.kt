package com.raerossi.notekeeper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raerossi.notekeeper.ui.features.login.LoginScreen
import com.raerossi.notekeeper.ui.features.main.MainScreen
import com.raerossi.notekeeper.ui.features.registration.RegistrationScreen
import com.raerossi.notekeeper.ui.features.splash.SplashScreen
import com.raerossi.notekeeper.ui.features.utils.SetSystemColors
import com.raerossi.notekeeper.ui.features.welcome.WelcomeScreen

@Composable
fun NoteKeeperApp() {
    val navController = rememberNavController()
    RootNavHost(navController)
}

@Composable
fun RootNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                onStartScreen = { onBoardingIsCompleted ->
                    navController.popBackStack()
                    if (onBoardingIsCompleted) navController.navigate(Graph.MAIN) else navController.navigate(Screen.WelcomeScreen.route)
                }
            )
        }
        composable(route = Screen.WelcomeScreen.route) {
            WelcomeScreen(
                onLogInClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.LoginScreen.route)
                },
                onRegistrationClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.RegistrationScreen.route)
                }
            )
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen()
        }
        composable(route = Screen.RegistrationScreen.route) {
            RegistrationScreen()
        }
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}

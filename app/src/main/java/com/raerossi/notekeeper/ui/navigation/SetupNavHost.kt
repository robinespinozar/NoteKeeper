package com.raerossi.notekeeper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.raerossi.notekeeper.ui.features.login.LoginScreen
import com.raerossi.notekeeper.ui.features.main.MainScreen
import com.raerossi.notekeeper.ui.features.registration.RegistrationScreen
import com.raerossi.notekeeper.ui.features.utils.SetSystemColors
import com.raerossi.notekeeper.ui.features.welcome.WelcomeScreen

@Composable
fun NoteKeeperApp(startDestination: String) {
    SetSystemColors(colorStatusBar = Color(0xFFFFFFFF), colorNavigationBar = Color(0xFFEFF3F4))
    val navController = rememberNavController()

    NoteKeeperNavHost(navController, startDestination)
}

@Composable
fun NoteKeeperNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
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
        composable(route = Screen.MainScreen.route) {
            MainScreen()
        }
    }
}

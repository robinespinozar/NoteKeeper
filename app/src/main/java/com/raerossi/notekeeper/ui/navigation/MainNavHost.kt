package com.raerossi.notekeeper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.raerossi.notekeeper.ui.features.home.HomeScreen
import com.raerossi.notekeeper.ui.features.login.LoginScreen
import com.raerossi.notekeeper.ui.features.main.MainScreen
import com.raerossi.notekeeper.ui.features.profile.ProfileScreen
import com.raerossi.notekeeper.ui.features.registration.SignUpScreen
import com.raerossi.notekeeper.ui.features.schedule.ScheduleScreen
import com.raerossi.notekeeper.ui.features.splash.SplashScreen
import com.raerossi.notekeeper.ui.features.welcome.WelcomeScreen

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = NavBarItem.HomeScreen.route
    ) {
        composable(route = NavBarItem.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = NavBarItem.ScheduleScreen.route) {
            ScheduleScreen()
        }
        composable(route = NavBarItem.ProfileScreen.route) {
            ProfileScreen()
        }
        userNavHost()
    }
}

fun NavGraphBuilder.userNavHost(){
    navigation(
        route = Graph.USER,
        startDestination = Screen.LoginScreen.route
    ){
        composable(route = Screen.LoginScreen.route) {
            LoginScreen()
        }
        composable(route = Screen.RegistrationScreen.route) {
            SignUpScreen()
        }
    }
}
package com.raerossi.notekeeper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.raerossi.notekeeper.ui.features.home.HomeScreen
import com.raerossi.notekeeper.ui.features.login.LoginScreen
import com.raerossi.notekeeper.ui.features.profile.ProfileScreen
import com.raerossi.notekeeper.ui.features.reestablish.ReestablishScreen
import com.raerossi.notekeeper.ui.features.registration.SignUpScreen
import com.raerossi.notekeeper.ui.features.schedule.ScheduleScreen
import com.raerossi.notekeeper.ui.features.verification.VerificationScreen

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
        userNavHost(navController)
    }
}

fun NavGraphBuilder.userNavHost(navController: NavHostController) {
    navigation(
        route = Graph.USER,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                onLoginClick = { isUserVerified ->
                    navController.popBackStack()
                    if (isUserVerified) navController.navigate(Graph.MAIN) else navController.navigate(Screen.VerificationScreen.route)
                },
                onSignUpClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.SignUpScreen.route)
                },
                onForgotPasswordClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.ReestablishScreen.route)
                }
            )
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(
                onSignUpClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.VerificationScreen.route)
                },
                onLogInClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.LoginScreen.route)
                }
            )
        }
        composable(route = Screen.VerificationScreen.route) {
            VerificationScreen(
                onContinueClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                })
        }
        composable(route = Screen.ReestablishScreen.route) {
            ReestablishScreen(
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.LoginScreen.route)
                })
        }
    }
}
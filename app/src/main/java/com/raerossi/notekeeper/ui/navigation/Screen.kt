package com.raerossi.notekeeper.ui.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen(route = "splash_screen")
    object WelcomeScreen : Screen(route = "welcome_screen")
    object LoginScreen : Screen(route = "login_screen")
    object SignUpScreen : Screen(route = "signup_screen")
    object ReestablishScreen : Screen(route = "reestablish_screen")
    object VerificationScreen : Screen(route = "verification_screen")
    object HomeScreen : Screen(route = "home_screen")
    object ScheduleScreen : Screen(route = "schedule_screen")
    object ProfileScreen : Screen(route = "profile_screen")
    object TaskScreen : Screen(route = "task_screen")
    object MainScreen : Screen(route = "main_screen")
}
package com.raerossi.notekeeper.ui.navigation

sealed class Screen(val route: String) {
    object WelcomeScreen : Screen(route = "welcome")
    object LoginScreen : Screen(route = "login")
    object RegistrationScreen : Screen(route = "registration")
    object MainScreen : Screen(route = "main")
}
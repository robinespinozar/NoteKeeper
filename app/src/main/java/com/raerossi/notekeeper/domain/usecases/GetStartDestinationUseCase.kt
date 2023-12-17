package com.raerossi.notekeeper.domain.usecases

import androidx.compose.runtime.MutableState
import com.raerossi.notekeeper.data.PreferencesRepository
import com.raerossi.notekeeper.ui.navigation.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStartDestinationUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(onPassStartScreen: (String) -> Unit) {
        repository.readOnBoardingState().collect { completed ->
           val startDestination = if (completed) Screen.MainScreen.route else Screen.WelcomeScreen.route
            onPassStartScreen(startDestination)
        }
    }
}
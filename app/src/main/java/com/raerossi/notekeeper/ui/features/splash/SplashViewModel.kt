package com.raerossi.notekeeper.ui.features.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.notekeeper.data.Preferences
import com.raerossi.notekeeper.data.PreferencesRepository
import com.raerossi.notekeeper.domain.usecases.GetStartDestinationUseCase
import com.raerossi.notekeeper.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class SplashViewModel @Inject constructor(
    //private val getStartDestinationUseCase: GetStartDestinationUseCase
    private val prefs: Preferences
) : ViewModel() {

    private val _isReady: MutableState<Boolean> = mutableStateOf(true)
    val isReady: State<Boolean> = _isReady

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.WelcomeScreen.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
//            _startDestination.value = getStartDestinationUseCase{  }
            prefs.readOnBoardingState().collect() { completed ->
                if (completed) {
                    _startDestination.value = Screen.MainScreen.route
                } else {
                    _startDestination.value = Screen.WelcomeScreen.route
                }
            //_startDestination.value = if (completed) Screen.MainScreen.route else Screen.WelcomeScreen.route
            }
            _isReady.value = false
        }
    }
}
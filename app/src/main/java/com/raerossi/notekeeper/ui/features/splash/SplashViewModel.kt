package com.raerossi.notekeeper.ui.features.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.notekeeper.domain.usecases.ReadOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readOnBoardingStateUseCase: ReadOnBoardingStateUseCase
) : ViewModel() {

    //private val _isReady: MutableState<Boolean> = mutableStateOf(true)
    //val isReady: State<Boolean> = _isReady

    private val _onBoardingIsCompleted = MutableStateFlow(false)
    val onBoardingIsCompleted: StateFlow<Boolean> = _onBoardingIsCompleted

    init {
        viewModelScope.launch {
            _onBoardingIsCompleted.value = readOnBoardingStateUseCase().stateIn(viewModelScope).value
            //_isReady.value = false
        }
    }
}
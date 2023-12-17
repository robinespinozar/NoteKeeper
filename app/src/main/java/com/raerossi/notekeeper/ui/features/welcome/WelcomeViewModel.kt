package com.raerossi.notekeeper.ui.features.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.notekeeper.domain.usecases.UpdateOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val updateOnBoardingStateUseCase: UpdateOnBoardingStateUseCase
) : ViewModel() {

    fun updateOnBoardingState(completed: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            updateOnBoardingStateUseCase(completed)
        }
    }
}
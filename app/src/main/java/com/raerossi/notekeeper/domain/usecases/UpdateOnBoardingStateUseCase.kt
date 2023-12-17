package com.raerossi.notekeeper.domain.usecases

import com.raerossi.notekeeper.data.PreferencesRepository
import javax.inject.Inject

class UpdateOnBoardingStateUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)
    }
}
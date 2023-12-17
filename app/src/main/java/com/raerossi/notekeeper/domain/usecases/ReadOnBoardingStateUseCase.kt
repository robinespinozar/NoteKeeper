package com.raerossi.notekeeper.domain.usecases

import com.raerossi.notekeeper.data.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoardingStateUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    operator fun invoke(): Flow<Boolean> = repository.readOnBoardingState()
}
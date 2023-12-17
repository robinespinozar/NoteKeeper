package com.raerossi.notekeeper.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesRepository @Inject constructor(private val prefs: Preferences) {

    suspend fun saveOnBoardingState(completed: Boolean) {
        prefs.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> = prefs.readOnBoardingState()
}
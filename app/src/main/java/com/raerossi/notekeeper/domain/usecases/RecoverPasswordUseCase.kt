package com.raerossi.notekeeper.domain.usecases

import android.util.Log
import com.raerossi.notekeeper.data.UserRepository
import javax.inject.Inject

class RecoverPasswordUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String): Boolean {
        val isEmailRegistred = repository.isEmailRegistred(email)
        return if (isEmailRegistred) repository.resetPassword(email) else false
    }
}
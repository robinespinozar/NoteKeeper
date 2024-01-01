package com.raerossi.notekeeper.domain.usecases

import com.raerossi.notekeeper.data.UserRepository
import javax.inject.Inject

class RecoverPasswordUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String) = repository.resetPassword(email)
}
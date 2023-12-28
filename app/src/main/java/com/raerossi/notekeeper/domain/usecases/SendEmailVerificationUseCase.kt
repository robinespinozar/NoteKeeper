package com.raerossi.notekeeper.domain.usecases

import com.raerossi.notekeeper.data.UserRepository
import javax.inject.Inject

class SendEmailVerificationUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.sendVerificationEmail()
}
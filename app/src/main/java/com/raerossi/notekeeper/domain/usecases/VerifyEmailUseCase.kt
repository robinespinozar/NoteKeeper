package com.raerossi.notekeeper.domain.usecases

import com.raerossi.notekeeper.data.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyEmailUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<Boolean> = repository.isVerifiedAccount
}
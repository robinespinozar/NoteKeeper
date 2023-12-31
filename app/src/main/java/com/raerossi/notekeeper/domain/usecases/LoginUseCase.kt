package com.raerossi.notekeeper.domain.usecases

import com.raerossi.notekeeper.data.UserRepository
import com.raerossi.notekeeper.data.remote.LoginResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): LoginResult {
        return repository.login(email,password)
    }
}
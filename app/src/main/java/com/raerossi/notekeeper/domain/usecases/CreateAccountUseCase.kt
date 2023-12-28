package com.raerossi.notekeeper.domain.usecases

import com.raerossi.notekeeper.data.UserRepository
import com.raerossi.notekeeper.ui.features.registration.SignUpUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(signUpUser: SignUpUser): Boolean {
        val isRegistrationSuccessful = repository.createAccount(signUpUser.email, signUpUser.password) != null
        return if (isRegistrationSuccessful) repository.createUser(signUpUser) else false
    }
}
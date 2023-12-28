package com.raerossi.notekeeper.data

import com.google.firebase.auth.AuthResult
import com.raerossi.notekeeper.data.remote.AuthService
import com.raerossi.notekeeper.data.remote.UserService
import com.raerossi.notekeeper.ui.features.registration.SignUpUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiAuth: AuthService,
    private val apiUser: UserService
) {
    suspend fun createAccount(email: String, password: String): AuthResult? {
        return apiAuth.createAccount(email, password)
    }

    suspend fun createUser(signUpUser: SignUpUser): Boolean {
        return apiUser.createUser(signUpUser)
    }

    suspend fun sendVerificationEmail(): Boolean {
        return apiAuth.sendVerificationEmail()
    }

    val isVerifiedAccount: Flow<Boolean> = apiAuth.isVerifiedAccount
}
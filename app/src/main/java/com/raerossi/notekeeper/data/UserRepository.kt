package com.raerossi.notekeeper.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.raerossi.notekeeper.data.remote.AuthService
import com.raerossi.notekeeper.data.remote.LoginResult
import com.raerossi.notekeeper.data.remote.UserService
import com.raerossi.notekeeper.ui.features.registration.SignUpUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiAuth: AuthService,
    private val apiUser: UserService
) {
    suspend fun createAccount(email: String, password: String): FirebaseUser? {
        return apiAuth.createAccount(email, password)
    }

    suspend fun createUser(signUpUser: SignUpUser): Boolean {
        return apiUser.createUser(signUpUser)
    }

    suspend fun sendVerificationEmail(): Boolean {
        return apiAuth.sendVerificationEmail()
    }

    val isVerifiedAccount: Flow<Boolean> = apiAuth.isVerifiedAccount

    suspend fun login(email: String, password: String): LoginResult {
        return apiAuth.login(email, password)
    }

    suspend fun resetPassword(email: String): Boolean {
        return apiAuth.resetPassword(email)
    }
}
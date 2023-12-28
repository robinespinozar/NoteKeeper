package com.raerossi.notekeeper.data.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthService @Inject constructor(
    private val auth: FirebaseAuth
) {

    val isVerifiedAccount: Flow<Boolean> = flow {
        while (true) {
            val verified = isEmailVerificated()
            emit(verified)
            delay(1000)
        }
    }

    suspend fun createAccount(email: String, password: String): AuthResult? {
        return auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun sendVerificationEmail(): Boolean {
        return runCatching {
            auth.currentUser?.sendEmailVerification()?.await() ?: false
        }.isSuccess
    }

    suspend fun isEmailVerificated(): Boolean {
        auth.currentUser?.reload()?.await()
        return auth.currentUser?.isEmailVerified ?: false
    }
}
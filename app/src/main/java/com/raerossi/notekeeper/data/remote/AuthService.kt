package com.raerossi.notekeeper.data.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    val isVerifiedAccount: Flow<Boolean> = flow {
        while (true) {
            val verified = isEmailVerificated()
            emit(verified)
            delay(1000)
        }
    }

    suspend fun createAccount(email: String, password: String): FirebaseUser? {
        return suspendCancellableCoroutine { cancellableContinuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val user = it.user
                cancellableContinuation.resume(user)
            }.addOnFailureListener {
                cancellableContinuation.resumeWithException(it)
            }
        }
    }

    suspend fun sendVerificationEmail(): Boolean {
        return runCatching {
            firebaseAuth.currentUser?.sendEmailVerification()?.await() ?: false
        }.isSuccess
    }

    private suspend fun isEmailVerificated(): Boolean {
        firebaseAuth.currentUser?.reload()?.await()
        return firebaseAuth.currentUser?.isEmailVerified ?: false
    }
}
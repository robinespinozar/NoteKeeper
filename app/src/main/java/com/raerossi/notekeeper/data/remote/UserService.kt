package com.raerossi.notekeeper.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.raerossi.notekeeper.ui.features.registration.SignUpUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserService @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    companion object {
        const val USER_COLLECTION = "users"
    }

    suspend fun createUser(signUpUser: SignUpUser): Boolean {
        return runCatching {
            val user = hashMapOf(
                "name" to signUpUser.name,
                "email" to signUpUser.email
            )
            firestore.collection(USER_COLLECTION).add(user).await()
        }.isSuccess
    }

    suspend fun isEmailRegistred(email: String): Boolean {
        val query = firestore.collection(USER_COLLECTION).whereEqualTo("email", email).get().await()
        val documents = query.documents
        return documents.isNotEmpty()
    }
}
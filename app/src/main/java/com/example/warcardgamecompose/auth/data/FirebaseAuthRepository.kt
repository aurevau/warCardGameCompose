package com.example.warcardgamecompose.auth.data

import com.example.warcardgamecompose.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(private val auth: FirebaseAuth): AuthRepository {
    override suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override fun logout() {
        auth.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? = auth.currentUser
}
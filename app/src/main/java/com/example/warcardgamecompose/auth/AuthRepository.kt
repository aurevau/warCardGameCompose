package com.example.warcardgamecompose.auth

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun login(email: String, password: String)
    suspend fun register(email: String, password: String)
    fun logout()
    fun getCurrentUser(): FirebaseUser?

}
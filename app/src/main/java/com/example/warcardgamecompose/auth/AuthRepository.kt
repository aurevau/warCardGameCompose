package com.example.warcardgamecompose.auth

import android.content.Intent
import android.content.IntentSender
import com.example.warcardgamecompose.auth.domain.SignInResult
import com.example.warcardgamecompose.auth.domain.UserData
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {


    suspend fun login(email: String, password: String)
    suspend fun loginWithGoogle(): SignInResult?
    suspend fun register(email: String, password: String)
    fun logout()
    fun getCurrentUser(): UserData?

}
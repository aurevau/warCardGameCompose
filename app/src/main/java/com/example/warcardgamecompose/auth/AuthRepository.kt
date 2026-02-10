package com.example.warcardgamecompose.auth

import android.app.Activity
import com.example.warcardgamecompose.auth.domain.SignInResult
import com.example.warcardgamecompose.auth.domain.UserData
import com.facebook.CallbackManager


interface AuthRepository {


    suspend fun login(email: String, password: String)
    suspend fun loginWithGoogle(): SignInResult?
    suspend fun loginWithFacebook(activity: Activity): SignInResult
    suspend fun register(email: String, password: String)
    fun logout()
    fun getCurrentUser(): UserData?

    fun getFacebookCallbackManager(): CallbackManager


}
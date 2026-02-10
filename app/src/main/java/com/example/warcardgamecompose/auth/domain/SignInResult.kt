package com.example.warcardgamecompose.auth.domain


import com.example.warcardgamecompose.auth.domain.UserData

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)


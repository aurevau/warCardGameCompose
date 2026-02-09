package com.example.warcardgamecompose.auth

sealed interface AuthStatus {
    data object Unknown: AuthStatus
    data object LoggedOut: AuthStatus
    data object LoggedIn: AuthStatus
}
package com.example.warcardgamecompose.auth

sealed interface AuthUiState {
    data object Idle: AuthUiState
    data object Loading: AuthUiState


    data object RegisterSuccess: AuthUiState
    data object LoginSuccess: AuthUiState

    data class Error(val message: String): AuthUiState

}
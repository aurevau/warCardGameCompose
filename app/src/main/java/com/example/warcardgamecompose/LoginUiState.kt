package com.example.warcardgamecompose

data class LoginUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null

)

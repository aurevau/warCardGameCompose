package com.example.warcardgamecompose.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository): ViewModel() {

    private val _authStatus = MutableStateFlow<AuthStatus>(AuthStatus.Unknown)
    val authStatus = _authStatus.asStateFlow()
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading

            try {
                repository.login(email,password)
                _uiState.value = AuthUiState.LoginSuccess
                _authStatus.value = AuthStatus.LoggedIn
            } catch (exception: Exception) {
                _uiState.value = AuthUiState.Error(exception.message ?: "Login Failed")
                _authStatus.value = AuthStatus.LoggedOut
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading

            try {
                repository.register(email, password)
                _uiState.value = AuthUiState.RegisterSuccess
            } catch (exception: Exception) {
                _uiState.value = AuthUiState.Error(exception.message ?: "Register failed")

            }
        }
    }

    fun logout() {
       repository.logout()
        _authStatus.value = AuthStatus.LoggedOut
    }

    fun currentUser() = repository.getCurrentUser()
}
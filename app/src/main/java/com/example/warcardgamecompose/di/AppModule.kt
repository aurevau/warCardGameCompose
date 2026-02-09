package com.example.warcardgamecompose.di

import com.example.warcardgamecompose.LoginViewModel
import com.example.warcardgamecompose.auth.AuthRepository
import com.example.warcardgamecompose.auth.AuthViewModel
import com.example.warcardgamecompose.auth.data.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { FirebaseAuth.getInstance() }

    single<AuthRepository> { FirebaseAuthRepository(get()) }
    viewModel {
        AuthViewModel(get())
    }

    viewModel { LoginViewModel() }
}
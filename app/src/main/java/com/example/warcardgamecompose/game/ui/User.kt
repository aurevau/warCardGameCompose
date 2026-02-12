package com.example.warcardgamecompose.game.ui

data class User(
    val id: String,
    val username: String,
    val avatarUrl: String? = null,
    val rating: Int = 0
)
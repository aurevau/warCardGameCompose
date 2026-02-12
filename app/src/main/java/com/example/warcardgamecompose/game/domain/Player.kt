package com.example.warcardgamecompose.game.domain

data class Player(var name: String) {
    val hand: MutableList<Card> = mutableListOf()
}
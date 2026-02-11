package com.example.warcardgamecompose.game.presentation

import com.example.warcardgamecompose.game.ui.User
import com.example.warcardgamecompose.game.domain.Card

data class PlayerState(
    val user: User,
    val deckSize: Int,
    val currentCard: Card? = null

)

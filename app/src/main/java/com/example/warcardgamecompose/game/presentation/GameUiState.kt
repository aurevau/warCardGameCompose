package com.example.warcardgamecompose.game.presentation

import com.example.warcardgamecompose.game.domain.Card

data class GameUiState(
    val playerCard: Card? = null,
    val opponentCard: Card? = null,

    val playerDeckSize: Int = 0,
    val opponentDeckSize: Int = 0,

    val status: GameStatus = GameStatus.WAITING_FOR_DEAL,

    val roundWinner: String? = null,
    val finalWinner: String? = null,

    val warCardsPlayer: List<Card> = emptyList(),
    val warCardsOpponent: List<Card> = emptyList(),

    val jokerTriggeredBy: String? = null,

    val isDealEnabled: Boolean = true
)

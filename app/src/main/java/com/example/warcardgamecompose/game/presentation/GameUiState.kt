package com.example.warcardgamecompose.game.presentation

import com.example.warcardgamecompose.game.domain.Card
import com.example.warcardgamecompose.game.domain.FinalResult
import com.example.warcardgamecompose.game.domain.JokerOwner
import com.example.warcardgamecompose.game.domain.RoundResult
import com.example.warcardgamecompose.game.domain.WarResult

data class GameUiState(
    val playerCard: Card? = null,
    val opponentCard: Card? = null,

    val playerDeckSize: Int = 0,
    val opponentDeckSize: Int = 0,

    val status: GameStatus = GameStatus.WAITING_FOR_DEAL,

    val roundWinner: RoundResult? = null,
    val finalWinner: FinalResult? = null,
    val warWinner: WarResult? = null,

    val warCardsPlayer: List<Card> = emptyList(),
    val warCardsOpponent: List<Card> = emptyList(),

    val jokerTriggeredBy: JokerOwner? = null,

    val isDealEnabled: Boolean = true
)

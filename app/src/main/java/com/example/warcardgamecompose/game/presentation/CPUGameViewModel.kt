package com.example.warcardgamecompose.game.presentation

import androidx.compose.ui.geometry.RoundRect
import androidx.lifecycle.ViewModel
import com.example.warcardgamecompose.game.domain.Card
import com.example.warcardgamecompose.game.domain.FinalResult
import com.example.warcardgamecompose.game.domain.Game
import com.example.warcardgamecompose.game.domain.GameMode
import com.example.warcardgamecompose.game.domain.RoundResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CPUGameViewModel: ViewModel() {

    private val game = Game()

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()

    fun startGame(mode: GameMode,
                  playerOneName: String,
                  playerTwoName: String = "CPU") {
        game.start(mode, playerOneName, playerTwoName)

        val(p1, p2) = game.getPlayers()

        _uiState.update {
            it.copy(
                playerDeckSize = p1.hand.size,
                opponentDeckSize = p2.hand.size,
                status = GameStatus.WAITING_FOR_DEAL,
                finalWinner = null,
                roundWinner = null,
                isDealEnabled = true
            )
        }
    }

    fun deal() {
        val (p1, p2) = game.getPlayers()

        val card1 = game.drawCard(p1)
        val card2 = game.drawCard(p2)

        _uiState.update {
            it.copy(playerCard = card1,
                opponentCard = card2,
                playerDeckSize = p1.hand.size,
                opponentDeckSize = p2.hand.size,
                status = GameStatus.SHOWING_RESULT)
        }
        if (card1 != null && card2 != null) {
            handleRound(card1,  card2)
        }


    }

    private fun handleRound(card1: Card, card2: Card) {
        val result = game.checkRoundWinner(card1, card2)

        when (result) {
            RoundResult.PLAYER1_WIN -> {
                game.awardCardsToPlayer1(card1, card2)
                updateAfterRound(RoundResult.PLAYER1_WIN)
            }

            RoundResult.PLAYER2_WIN -> {
                game.awardCardsToPlayer2(card1, card2)
                updateAfterRound(RoundResult.PLAYER2_WIN)
            }

            RoundResult.TIE -> {
                _uiState.update {
                    it.copy(
                        status = GameStatus.WAR,
                        roundWinner = null,
                        isDealEnabled = false
                    )
                }
            }

            RoundResult.JOKER_P1 -> {
                game.handleJokerPlayer1(card1, card2)
                updateAfterRound(RoundResult.JOKER_P1)
            }

            RoundResult.JOKER_P2 -> {
                game.handleJokerPlayer2(card1, card2)
                updateAfterRound(RoundResult.JOKER_P2)
            }
        }
    }

    private fun updateAfterRound(result: RoundResult) {
        val (p1, p2) = game.getPlayers()
        if(game.isGameOver()) {
            val finalWinner = game.getFinalWinner()

            _uiState.update {
                it.copy(
                    status = GameStatus.FINISHED,
                    finalWinner = if (finalWinner == p1) FinalResult.PLAYER1 else FinalResult.PLAYER2,
                    isDealEnabled = false,
                    playerDeckSize =  p1.hand.size,
                    opponentDeckSize = p2.hand.size
                    )
            }
        } else {
            _uiState.update {
                it.copy(roundWinner = result,
                    isDealEnabled = true,
                    status = GameStatus.WAITING_FOR_DEAL,
                    playerDeckSize = p1.hand.size,
                    opponentDeckSize = p2.hand.size)
            }
        }
    }


}
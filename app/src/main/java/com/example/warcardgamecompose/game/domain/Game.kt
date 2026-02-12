package com.example.warcardgamecompose.game.domain

class Game {


    companion object {
        private const val JOKER_STEAL_COUNT = 5
    }

    private val deck = Deck()
    private val warPot = mutableListOf<Card>()


    private lateinit var player1: Player
    private lateinit var player2: Player

    fun start(mode: GameMode,
              playerOneName: String,
              playerTwoName: String) {

        when(mode) {
            GameMode.CPU -> {
                player1 = Player(playerOneName)
                player2 = Player("CPU")
            }
            GameMode.PVP -> {
                player1 = Player(playerOneName)
                player2 = Player(playerTwoName)

            }

            GameMode.ONLINE -> {
                // Fix multiplayer with backend later
            }
        }

        deck.initialize()
        dealCards(player1, player2)

    }

    fun dealCards(player1: Player, player2: Player) {
       player1.hand.clear()
       player2.hand.clear()

       var toP1 = true
       while(!deck.isEmpty()) {
           val card: Card? = deck.draw()
           if (toP1) {
               player1.hand.add(card!!)
           } else {
               player2.hand.add(card!!)
           }
           toP1 = !toP1
       }
    }

    fun checkRoundWinner(card1: Card, card2: Card): RoundResult {
        return when {
            card1.rank == Rank.JOKER && card2.rank == Rank.JOKER -> RoundResult.TIE

            card1.rank == Rank.JOKER -> RoundResult.JOKER_P1

            card2.rank == Rank.JOKER -> RoundResult.JOKER_P2

            card1.rank.value > card2.rank.value -> RoundResult.PLAYER1_WIN

            card2.rank.value > card1.rank.value -> RoundResult.PLAYER2_WIN

            else -> RoundResult.TIE
        }
    }

    fun drawCard(player: Player): Card? {
        return if (player.hand.isNotEmpty()) {
            player.hand.removeAt(0)
        } else null
    }

    fun isGameOver(): Boolean = player1.hand.isEmpty() || player2.hand.isEmpty()

    fun getFinalWinner(): Player? {
        return when {
            player1.hand.isEmpty() && player2.hand.isNotEmpty() -> player2
            player2.hand.isEmpty() && player1.hand.isNotEmpty() -> player1
            else -> null
        }
    }

    fun awardCardsToPlayer2(card1: Card, card2: Card) {
        player2.hand.add(card1)
        player2.hand.add(card2)

        if(warPot.isNotEmpty()) {
            player2.hand.addAll(warPot)
            warPot.clear()
        }
    }

    fun awardCardsToPlayer1(card1: Card, card2: Card) {
        player1.hand.add(card1)
        player1.hand.add(card2)

        if(warPot.isNotEmpty()) {
            player1.hand.addAll(warPot)
            warPot.clear()
        }
    }

    fun handleJokerPlayer1(card1: Card, card2: Card) {

        val stolen = stealCards(from = player2, count = JOKER_STEAL_COUNT)

        player1.hand.add(card1)
        player1.hand.add(card2)
        player1.hand.addAll(stolen)

        collectWarPot(player1)
    }

    fun handleJokerPlayer2(card1: Card, card2: Card) {

        val stolen = stealCards(from = player1, count = JOKER_STEAL_COUNT)

        player2.hand.add(card1)
        player2.hand.add(card2)
        player2.hand.addAll(stolen)

        collectWarPot(player2)
    }

    private fun stealCards(from: Player, count: Int): List<Card> {
        val stolen = mutableListOf<Card>()

        repeat(count) {
            if (from.hand.isNotEmpty()) {
                stolen.add(from.hand.removeAt(0))
            }
        }

        return stolen
    }

    private fun collectWarPot(winner: Player) {
        if (warPot.isNotEmpty()) {
            winner.hand.addAll(warPot)
            warPot.clear()
        }
    }

    fun getPlayers(): Pair<Player, Player> =
        player1 to player2

}

package com.example.warcardgamecompose.game.domain

class Deck {
    private val cards = mutableListOf<Card>()

    fun initialize() {
        cards.clear()

        for (suit in Suit.entries) {
            for (rank in Rank.entries) {
                if (rank != Rank.JOKER) {
                    cards.add(Card(suit = suit, rank = rank))
                }
            }
        }

        cards.add(Card(suit = null, rank = Rank.JOKER))
        cards.add(Card(suit = null, rank = Rank.JOKER))

        cards.shuffle()
    }

    fun draw(): Card? = if (cards.isNotEmpty()) cards.removeAt(0) else null

    fun size(): Int = cards.size

    fun isEmpty(): Boolean = cards.isEmpty()

}
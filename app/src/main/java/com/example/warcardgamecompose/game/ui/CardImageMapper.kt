package com.example.warcardgamecompose.game.ui

import com.example.warcardgamecompose.R
import com.example.warcardgamecompose.game.domain.Card
import com.example.warcardgamecompose.game.domain.Rank
import com.example.warcardgamecompose.game.domain.Suit

object CardImageMapper {
    fun imageFor(card: Card?): Int {
        return when (card?.rank) {
            Rank.JOKER -> R.drawable.joker

            else -> when (card?.suit) {
                Suit.HEARTS -> hearts(card.rank)
                Suit.DIAMONDS -> diamonds(card.rank)
                Suit.CLUBS -> clubs(card.rank)
                Suit.SPADES -> spades(card.rank)

                null -> R.drawable.joker
            }
        }

    }

    private fun hearts(rank: Rank) = when (rank) {
        Rank.ACE -> R.drawable.ace_heart
        Rank.TWO -> R.drawable.two_heart
        Rank.THREE -> R.drawable.three_heart
        Rank.FOUR -> R.drawable.four_heart
        Rank.FIVE -> R.drawable.five_heart
        Rank.SIX -> R.drawable.six_heart
        Rank.SEVEN -> R.drawable.seven_heart
        Rank.EIGHT -> R.drawable.eight_heart
        Rank.NINE -> R.drawable.nine_heart
        Rank.TEN -> R.drawable.ten_heart
        Rank.JACK -> R.drawable.knight_heart
        Rank.QUEEN -> R.drawable.queen_heart
        Rank.KING -> R.drawable.knight_heart
        Rank.JOKER -> R.drawable.joker
    }

    private fun diamonds(rank: Rank) = when (rank) {
        Rank.ACE -> R.drawable.ace_diamond
        Rank.TWO -> R.drawable.two_diamond
        Rank.THREE -> R.drawable.three_diamond
        Rank.FOUR -> R.drawable.four_diamond
        Rank.FIVE -> R.drawable.five_diamond
        Rank.SIX -> R.drawable.six_diamond
        Rank.SEVEN -> R.drawable.seven_diamond
        Rank.EIGHT -> R.drawable.eight_diamond
        Rank.NINE -> R.drawable.nine_diamond
        Rank.TEN -> R.drawable.ten_diamond
        Rank.JACK -> R.drawable.knight_diamond
        Rank.QUEEN -> R.drawable.queen_diamond
        Rank.KING -> R.drawable.king_diamond
        Rank.JOKER -> R.drawable.joker
    }

    private fun clubs(rank: Rank) = when (rank) {
        Rank.ACE -> R.drawable.ace_clover
        Rank.TWO -> R.drawable.two_clover
        Rank.THREE -> R.drawable.three_clover
        Rank.FOUR -> R.drawable.four_clover
        Rank.FIVE -> R.drawable.five_clover
        Rank.SIX -> R.drawable.six_clover
        Rank.SEVEN -> R.drawable.seven_clover
        Rank.EIGHT -> R.drawable.eight_clover
        Rank.NINE -> R.drawable.nine_clover
        Rank.TEN -> R.drawable.ten_clover
        Rank.JACK -> R.drawable.knight_clover
        Rank.QUEEN -> R.drawable.queen_clover
        Rank.KING -> R.drawable.king_clover
        Rank.JOKER -> R.drawable.joker
    }

    private fun spades(rank: Rank) = when (rank) {
        Rank.ACE -> R.drawable.ace_spade
        Rank.TWO -> R.drawable.two_spade
        Rank.THREE -> R.drawable.three_spade
        Rank.FOUR -> R.drawable.four_spade
        Rank.FIVE -> R.drawable.five_spade
        Rank.SIX -> R.drawable.six_spade
        Rank.SEVEN -> R.drawable.seven_spade
        Rank.EIGHT -> R.drawable.eight_spade
        Rank.NINE -> R.drawable.nine_spade
        Rank.TEN -> R.drawable.ten_spade
        Rank.JACK -> R.drawable.knight_spade
        Rank.QUEEN -> R.drawable.queen_spade
        Rank.KING -> R.drawable.king_spade
        Rank.JOKER -> R.drawable.joker
    }
}
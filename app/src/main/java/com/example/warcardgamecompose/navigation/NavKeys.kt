package com.example.warcardgamecompose.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination: NavKey

@Serializable
data object RegisterDestination: NavKey

@Serializable
data object ChooseGameModeScreenDestination: NavKey

@Serializable
data object CPUGameScreenDestination: NavKey

@Serializable
data class MultiplayerScreenDestination(val gameId: String): NavKey

@Serializable
data object PvPGameScreenDestination: NavKey

@Serializable
data class ProfileScreenDestination(val userId: String): NavKey
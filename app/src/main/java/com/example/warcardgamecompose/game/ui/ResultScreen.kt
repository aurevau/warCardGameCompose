package com.example.warcardgamecompose.game.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warcardgamecompose.R
import com.example.warcardgamecompose.game.domain.FinalResult
import com.example.warcardgamecompose.ui.theme.Black
import com.example.warcardgamecompose.ui.theme.ShootingStar
import com.example.warcardgamecompose.ui.theme.WarCardGameComposeTheme
import com.example.warcardgamecompose.ui.theme.components.CartoonLoginButton
import com.example.warcardgamecompose.ui.theme.components.CartoonTextBox
import com.example.warcardgamecompose.ui.theme.components.StrokeText

@Composable
fun ResultScreen(
    winner: FinalResult,
    onExitButtonClick: () -> Unit
) {

    val message = when (winner) {
        FinalResult.PLAYER1 -> "You won the game"
        FinalResult.PLAYER2 -> "CPU won the game"
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {

            Image(painter = painterResource(R.drawable.throphy), contentDescription = null,
                modifier = Modifier.fillMaxSize())
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                StrokeText(
                    message,
                    fillColor = Color.White,
                    strokeColor = Black,
                    fontSize = 28.sp,
                    fontFamily = ShootingStar
                )

                Spacer(modifier = Modifier.height(32.dp))

                CartoonTextBox("Play Again", onClick = {}, enabled = true)
                CartoonTextBox("New Game", onClick = {}, enabled = true)


            }
        }
    }

}



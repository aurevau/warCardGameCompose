package com.example.warcardgamecompose.game.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warcardgamecompose.R
import com.example.warcardgamecompose.game.domain.FinalResult
import com.example.warcardgamecompose.game.domain.GameMode
import com.example.warcardgamecompose.game.domain.RoundResult
import com.example.warcardgamecompose.game.presentation.CPUGameViewModel
import com.example.warcardgamecompose.game.presentation.GameStatus
import com.example.warcardgamecompose.ui.theme.Black
import com.example.warcardgamecompose.ui.theme.ShootingStar
import com.example.warcardgamecompose.ui.theme.WarCardGameComposeTheme
import com.example.warcardgamecompose.ui.theme.White
import com.example.warcardgamecompose.ui.theme.components.CartoonTextBox
import com.example.warcardgamecompose.ui.theme.components.StrokeText
import org.koin.androidx.compose.koinViewModel

@Composable
fun CPUGameScreen(
    viewModel: CPUGameViewModel = koinViewModel(),
    onExitButtonClick: () -> Unit,
    onWarStarted: () -> Unit,
    onGameFinished: (FinalResult) -> Unit,

) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.startGame(GameMode.CPU, "player1", "player2")

    }

    LaunchedEffect(state.status) {
        when(state.status) {
            GameStatus.FINISHED -> {
                state.finalWinner?.let(onGameFinished)
            }
            GameStatus.WAR -> {onWarStarted()}
            else -> Unit
        }

    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)) {

            IconButton(onClick = {
                onExitButtonClick()
            }, modifier = Modifier.align(Alignment.TopEnd)) {
                Icon(painter = painterResource(R.drawable.exitbutton),
                contentDescription = stringResource(R.string.exit_button),
                    modifier = Modifier.size(100.dp),
                    tint = Color.Unspecified)
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(80.dp))

                Image(painter = painterResource(R.drawable.logo),
                    contentDescription = stringResource(R.string.logo),
                    modifier = Modifier
                        .height(100.dp)
                        .padding(horizontal = 16.dp),
                    contentScale = ContentScale.FillHeight
                    )

                Spacer(modifier = Modifier.height(60.dp))

                state.roundWinner?.let {result ->
                    val message = when (result) {
                        RoundResult.PLAYER1_WIN -> "You win the round"
                        RoundResult.PLAYER2_WIN -> "CPU wins the round"
                        RoundResult.JOKER_P1 -> "JOKER! You steal five cards"
                        RoundResult.JOKER_P2 -> "JOKER! CPU steals five cards"
                        RoundResult.TIE -> "WAR!"
                    }

                    if (message.isNotEmpty()){
                        StrokeText(message, fillColor = Color.White, strokeColor = Black, fontSize = 16.sp, fontFamily = ShootingStar)

                    }
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(CardImageMapper.imageFor(state.opponentCard)),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .height(200.dp),
                        contentScale = ContentScale.FillHeight,
                        contentDescription = stringResource(R.string.left_card)
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                    Image(painter = painterResource(CardImageMapper.imageFor(state.playerCard)),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .height(200.dp),
                        contentScale = ContentScale.FillHeight,
                        contentDescription = stringResource(R.string.right_card)
                    )

                    Spacer(modifier = Modifier.width(20.dp))






                }

                Spacer(modifier = Modifier.height(50.dp))


                CartoonTextBox("DEAL", onClick = { viewModel.deal() }, modifier = Modifier.width(200.dp),
                    enabled = state.isDealEnabled)

                Spacer(modifier = Modifier.height(60.dp))



                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center) {
                    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                        StrokeText("Opponent", fontSize = 18.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)
                        StrokeText(state.opponentDeckSize.toString(), fontSize = 40.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)



                    }
                    Spacer(modifier = Modifier.width(60.dp))


                    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                        StrokeText("Player", fontSize = 18.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)
                        StrokeText(state.playerDeckSize.toString(), fontSize = 40.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)



                    }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CPUGameScreenPreview() {
    WarCardGameComposeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        CPUGameScreen(
            onExitButtonClick = {},
            onGameFinished = {},
            onWarStarted = {}
        )
    }
}
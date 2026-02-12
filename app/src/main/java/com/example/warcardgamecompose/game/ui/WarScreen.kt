package com.example.warcardgamecompose.game.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import com.example.warcardgamecompose.game.domain.RoundResult
import com.example.warcardgamecompose.game.domain.WarResult
import com.example.warcardgamecompose.game.presentation.CPUGameViewModel
import com.example.warcardgamecompose.ui.theme.Black
import com.example.warcardgamecompose.ui.theme.ShootingStar
import com.example.warcardgamecompose.ui.theme.WarCardGameComposeTheme
import com.example.warcardgamecompose.ui.theme.components.StrokeText
import org.koin.androidx.compose.koinViewModel

@Composable
fun WarScreen(
    viewModel: CPUGameViewModel = koinViewModel(),
    onExitButtonClick: () -> Unit,
    onWarFinished: (WarResult) -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {

            IconButton(
                onClick = {}, modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.exitbutton),
                    contentDescription = stringResource(R.string.exit_button),
                    modifier = Modifier.size(100.dp),
                    tint = Color.Unspecified
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                state.warCardsOpponent.forEach {
                    Image(
                        painter = painterResource(
                            CardImageMapper.imageFor(it)
                        ), modifier = Modifier.height(100.dp), contentDescription = null,
                        contentScale = ContentScale.FillHeight
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            state.roundWinner?.let { result ->
                val message = when (result) {
                    RoundResult.TIE -> "It's a tie, try again!"
                    RoundResult.PLAYER1_WIN -> "You win the round!"
                    RoundResult.PLAYER2_WIN -> "CPU wins the round!"
                    RoundResult.JOKER_P1 -> "JOKER! You steal five extra cards"
                    RoundResult.JOKER_P2 -> "JOKER! CPU steals five extra cards"
                }

                if (message.isNotEmpty()) {
                    StrokeText(
                        text = message,
                        fillColor = Color.White,
                        strokeColor = Black,
                        fontSize = 16.sp,
                        fontFamily = ShootingStar
                    )
                }





            }




            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                state.warCardsPlayer.forEach {
                    Image(
                        painter = painterResource(
                            CardImageMapper.imageFor(it)
                        ),
                        modifier = Modifier.height(100.dp),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight
                    )
                }


            }

        }

    }
}

@Preview
@Composable
fun WarScreenPreview() {
    WarCardGameComposeTheme(darkTheme = false,
        dynamicColor = false) {
        WarScreen(onWarFinished = {},
            onExitButtonClick = {})
    }
}
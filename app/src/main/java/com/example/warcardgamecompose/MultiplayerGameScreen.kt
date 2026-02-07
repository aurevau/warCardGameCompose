package com.example.warcardgamecompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warcardgamecompose.ui.theme.Black
import com.example.warcardgamecompose.ui.theme.ShootingStar
import com.example.warcardgamecompose.ui.theme.WarCardGameComposeTheme
import com.example.warcardgamecompose.ui.theme.White
import com.example.warcardgamecompose.ui.theme.components.CartoonBox
import com.example.warcardgamecompose.ui.theme.components.StrokeText

@Composable
fun MultiplayerGameScreen(
    onDealButtonClick: () -> Unit,
    onExitButtonClick: () -> Unit
) {
    var opponentUsername by remember { mutableStateOf("Opponent username") }
    var playerUsername by remember { mutableStateOf("Player username") }

    var opponentProfilePicture by remember {mutableStateOf(R.drawable.skater)}
    var opponentCard by remember { mutableStateOf(R.drawable.back_card) }
    var playerCard by remember { mutableStateOf(R.drawable.back_card) }

    var playerScore by rememberSaveable { mutableStateOf(0) }
    var opponentScore by rememberSaveable { mutableIntStateOf(0) }


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
                    tint = Color.Unspecified
                )
            }



            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(Modifier.height(20.dp))
                Image(painter = painterResource(opponentProfilePicture),
                    contentDescription = stringResource(R.string.opponent_profile_picture),
                    modifier = Modifier.height(80.dp)
                        .padding(horizontal = 16.dp),
                    contentScale = ContentScale.FillHeight
                )

                        StrokeText(
                            text = opponentUsername,
                            fontSize = 18.sp,
                            fontFamily = ShootingStar,
                            fillColor = White,
                            strokeColor = Black
                        )


                        StrokeText(
                            modifier = Modifier.offset(y = (-26).dp),
                            text = opponentScore.toString(),
                            fontSize = 40.sp,
                            fontFamily = ShootingStar,
                            fillColor = White,
                            strokeColor = Black
                        )

                        Image(painter = painterResource(opponentCard),
                            modifier = Modifier.offset(y = (-30.dp))
                                .height(200.dp),
                            contentScale = ContentScale.FillHeight,
                            contentDescription = stringResource(R.string.left_card)
                        )

                        Image(painter = painterResource(playerCard),
                            modifier = Modifier
                                .offset(y = (-20.dp))
                                .height(200.dp),
                            contentScale = ContentScale.FillHeight,
                            contentDescription = stringResource(R.string.right_card)
                        )

                        Spacer(Modifier.weight(0.5f))


                        CartoonBox("DEAL", onClick = { onDealButtonClick() }, modifier = Modifier.width(200.dp))
                        Spacer(Modifier.height(10.dp))

                    StrokeText(playerUsername, fontSize = 18.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)
                    StrokeText(playerScore.toString(), fontSize = 40.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black,                             modifier = Modifier.offset(y = (-26).dp))














            }
        }


    }


}


@Preview(showBackground = true)
@Composable
fun MultiplayerGameScreenPreview() {
    WarCardGameComposeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {

        MultiplayerGameScreen(
            onExitButtonClick = {},
            onDealButtonClick = {}
        )
    }

}
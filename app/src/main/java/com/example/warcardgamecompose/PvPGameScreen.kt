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
import androidx.compose.ui.graphics.graphicsLayer
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
import com.example.warcardgamecompose.ui.theme.components.CartoonTextBox
import com.example.warcardgamecompose.ui.theme.components.StrokeText

@Composable
fun PvPGameScreen(
    onOpponentDealButtonClick: () -> Unit,
    onOpponentExitButtonClick: () -> Unit,
    onPlayerDealButtonClick: () -> Unit,
    onPlayerExitButtonClick: () -> Unit

) {

    var opponentUsername by remember { mutableStateOf("Opponent username") }
    var playerUsername by remember { mutableStateOf("Player username") }

    var opponentProfilePicture by remember {mutableStateOf(R.drawable.skater)}
    var opponentCard by remember { mutableStateOf(R.drawable.back_card) }
    var playerCard by remember { mutableStateOf(R.drawable.back_card) }

    var playerScore by rememberSaveable { mutableStateOf(0) }
    var opponentScore by rememberSaveable { mutableIntStateOf(0) }

    Surface(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)) {

            IconButton(onClick = {
                onOpponentExitButtonClick()
            }, modifier = Modifier.align(Alignment.TopStart)) {
                Icon(painter = painterResource(R.drawable.exitbutton),
                    contentDescription = stringResource(R.string.exit_button),
                    modifier = Modifier.size(100.dp),
                    tint = Color.Unspecified
                )

            }

            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally){


                Column(modifier = Modifier.fillMaxWidth()
                    .graphicsLayer {
                        scaleY = -1f
                    }, horizontalAlignment = Alignment.CenterHorizontally) {


                    Spacer(modifier = Modifier.height(30.dp))
                    CartoonTextBox("DEAL", onClick = { onOpponentDealButtonClick() }, modifier = Modifier.width(200.dp))
                    Spacer(modifier = Modifier.height(10.dp))

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


                }

                Box(modifier = Modifier.fillMaxWidth().weight(1f),
                    contentAlignment = Alignment.Center) {

                    Image(painter = painterResource(opponentCard),
                        contentDescription = stringResource(R.string.left_card),
                        modifier = Modifier.height(200.dp)
                            .offset(x = (-80).dp, y = (-80).dp)
                            .graphicsLayer{
                                rotationZ = -180f
                            },
                        contentScale = ContentScale.FillHeight
                    )




                    Image(painter = painterResource(playerCard),
                        contentDescription = stringResource(R.string.right_card),
                        modifier = Modifier.height(200.dp)
                            .offset(x = (80).dp, y = (80).dp),
                        contentScale = ContentScale.FillHeight
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))


                CartoonTextBox("DEAL", onClick = { onPlayerDealButtonClick() }, modifier = Modifier.width(200.dp))
                Spacer(modifier = Modifier.height(10.dp))

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






            }





            IconButton(onClick = {
                onPlayerExitButtonClick()
            }, modifier = Modifier.align(Alignment.BottomEnd)) {

                Icon(painter = painterResource(R.drawable.exitbutton),
                    contentDescription = stringResource(R.string.exit_button),
                    modifier = Modifier.size(100.dp),
                    tint = Color.Unspecified)

            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun PvPGameScreenPreview(){
    WarCardGameComposeTheme(
        darkTheme = false,
        dynamicColor = false) {

        PvPGameScreen(
            onPlayerDealButtonClick = {},
            onPlayerExitButtonClick = {},
            onOpponentDealButtonClick = {},
            onOpponentExitButtonClick = {}
        )

    }
}
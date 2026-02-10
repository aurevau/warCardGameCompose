package com.example.warcardgamecompose

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
import com.example.warcardgamecompose.ui.theme.components.CartoonTextBox
import com.example.warcardgamecompose.ui.theme.components.StrokeText

@Composable
fun CPUGameScreen(
    onDealButtonClick: () -> Unit,
    onExitButtonClick: () -> Unit
) {

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

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(opponentCard),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .height(200.dp),
                        contentScale = ContentScale.FillHeight,
                        contentDescription = stringResource(R.string.left_card)
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                    Image(painter = painterResource(playerCard),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .height(200.dp),
                        contentScale = ContentScale.FillHeight,
                        contentDescription = stringResource(R.string.right_card)
                    )

                    Spacer(modifier = Modifier.width(20.dp))






                }

                Spacer(modifier = Modifier.height(50.dp))


                CartoonTextBox("DEAL", onClick = { onDealButtonClick() }, modifier = Modifier.width(200.dp))

                Spacer(modifier = Modifier.height(60.dp))



                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center) {
                    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                        StrokeText("Opponent", fontSize = 18.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)
                        StrokeText(opponentScore.toString(), fontSize = 40.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)



                    }
                    Spacer(modifier = Modifier.width(60.dp))


                    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                        StrokeText("Player", fontSize = 18.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)
                        StrokeText(playerScore.toString(), fontSize = 40.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)



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
            onDealButtonClick = {},
            onExitButtonClick = {}
        )
    }
}
package com.example.warcardgamecompose
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
fun ChooseGameModeScreen(
    onPvpButtonClick: () -> Unit,
    onCPUButtonClick: () -> Unit, 
    onMultiplayerButtonClick: () -> Unit,
    onExitButtonClick: () -> Unit


) {


    Surface(
        Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {

            IconButton(
                onClick = {onExitButtonClick()},
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.exitbutton),
                    contentDescription = stringResource(R.string.exit_button),
                    modifier = Modifier.size(100.dp),
                    tint = Color.Unspecified
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(80.dp))


                StrokeText(text = stringResource(R.string.welcome), fontSize = 50.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)


                Image(
                    painter = painterResource(R.drawable.skater),
                    contentDescription = stringResource(R.string.logo),
                    modifier = Modifier
                        .height(80.dp)
                        .padding(horizontal = 16.dp),
                    contentScale = ContentScale.FillHeight
                )

                StrokeText(text = stringResource(R.string.username), fontSize = 18.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)

                Spacer(modifier = Modifier.height(40.dp))

                StrokeText(text = stringResource(R.string.please_choose_your_game_mode), fontSize = 18.sp, fontFamily = ShootingStar, fillColor = White, strokeColor = Black)







                Spacer(modifier = Modifier.height(10.dp))

                CartoonTextBox("PvP", onClick = { onPvpButtonClick() }, enabled = true)
                Spacer(modifier = Modifier.height(16.dp))

                CartoonTextBox("CPU", onClick = { onCPUButtonClick() }, enabled = true)

                Spacer(modifier = Modifier.height(16.dp))

                CartoonTextBox("Multiplayer", onClick = { onMultiplayerButtonClick() }, enabled = true)


            }
        }

    }

}

@Preview(showBackground = true,)
@Composable
fun ChooseGameModePreview(

) {

    WarCardGameComposeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        ChooseGameModeScreen(
            onExitButtonClick = {},
            onCPUButtonClick = {},
            onPvpButtonClick = {},
            onMultiplayerButtonClick = {},

        )
    }
}
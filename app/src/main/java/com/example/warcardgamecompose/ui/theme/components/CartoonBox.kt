package com.example.warcardgamecompose.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warcardgamecompose.R
import com.example.warcardgamecompose.ui.theme.ShootingStar
import com.example.warcardgamecompose.ui.theme.WarCardGameComposeTheme
import kotlin.text.uppercase


@Composable
fun CartoonTextBox(
    placeholder: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable{onClick()}
            .height(60.dp)
            .padding(horizontal = 20.dp)

    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = 3.dp, y = 3.dp)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(6.dp)
                )
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(6.dp)
                )
                .border(
                    width = 1.2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            StrokeText(
                text = placeholder.uppercase(),
                fontSize = 28.sp,
                fillColor = Color.White,
                strokeColor = Color.Black,
                fontFamily = ShootingStar
            )
        }
    }

}

@Composable
fun CartoonLoginButton(
    placeholder: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable{onClick()}
            .height(60.dp)
            .padding(horizontal = 20.dp)

    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = 3.dp, y = 3.dp)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(6.dp)
                )
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(6.dp)
                )
                .border(
                    width = 1.2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {



            Image(painter = painterResource(placeholder),
                contentDescription = "Login Icon",
                modifier = Modifier.fillMaxHeight()
            )
        }
    }



}



@Preview
@Composable
fun CartoonLoginButtonPreview(){
    WarCardGameComposeTheme {
        CartoonLoginButton(
            R.drawable.login_fb

        ) { }
    
    }
}

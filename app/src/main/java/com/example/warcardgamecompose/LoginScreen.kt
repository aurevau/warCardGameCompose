package com.example.warcardgamecompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warcardgamecompose.ui.theme.WarCardGameComposeTheme
import com.example.warcardgamecompose.ui.theme.components.CartoonBox
import com.example.warcardgamecompose.ui.theme.components.CartoonTextField

@Composable
fun LoginScreen(state: LoginUiState,
                onEmailChange: (String) -> Unit,
                onPasswordChange: (String) -> Unit,
                onLoginButtonClick: () -> Unit,
                onRegisterButtonClick: () -> Unit,
                onGuestButtonClick: () -> Unit) {

    Surface() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.logo),
            modifier = Modifier
                .height(100.dp)
                .padding(horizontal = 16.dp),
            contentScale = ContentScale.FillHeight
        )

        Spacer(modifier = Modifier.height(60.dp))



        CartoonTextField(
            value = state.email,
            onValueChange = onEmailChange,
            placeholder = stringResource(R.string.email),
            isPassword = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))


        CartoonTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            placeholder = stringResource(R.string.password),
            isPassword = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )



        Spacer(modifier = Modifier.height(40.dp))




        CartoonBox("LOGIN", onClick = {onLoginButtonClick()})
        Spacer(modifier = Modifier.height(16.dp))
        CartoonBox("REGISTER", onClick = {onRegisterButtonClick()})
        Spacer(modifier = Modifier.height(16.dp))
        CartoonBox("GUEST", onClick = {onGuestButtonClick()})




    }
        }


}

@Preview(showBackground = true,)
@Composable
fun LoginScreenPreview(

) {

    WarCardGameComposeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        LoginScreen(
            state = LoginUiState(
                email = "test@test.com",
                password = "password"
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onLoginButtonClick = {},
            onRegisterButtonClick = {},
            onGuestButtonClick = {}
        )
    }
}






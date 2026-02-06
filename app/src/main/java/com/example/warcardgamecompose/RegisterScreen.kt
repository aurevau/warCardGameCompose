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
import com.example.warcardgamecompose.ui.theme.WarCardGameComposeTheme
import com.example.warcardgamecompose.ui.theme.components.CartoonBox
import com.example.warcardgamecompose.ui.theme.components.CartoonTextField

@Composable
fun RegisterScreen(state: LoginUiState,
                   onUsernameChange: (String) -> Unit,
                   onEmailChange: (String) -> Unit,
                   onPasswordChange: (String) -> Unit,
                   onRegisterButtonClick: () -> Unit

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
                onClick = {},
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
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(80.dp))


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
                    value = state.username, onValueChange = onUsernameChange,
                    placeholder = stringResource(R.string.username),
                    isPassword = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                CartoonTextField(
                    value = state.email, onValueChange = onEmailChange,
                    placeholder = stringResource(R.string.email),
                    isPassword = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                CartoonTextField(
                    value = state.password, onValueChange = onPasswordChange,
                    placeholder = stringResource(R.string.password),
                    isPassword = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))

                CartoonBox("REGISTER", onClick = { onRegisterButtonClick() })


            }
        }

    }
}

@Preview(showBackground = true,)
@Composable
fun RegisterScreenPreview(

) {

    WarCardGameComposeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        RegisterScreen(state = LoginUiState(
            email = "test@test.com",
            password = "password",
            username = "aujiva"

        ),
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onRegisterButtonClick = {}
            )
    }
}

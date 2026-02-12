package com.example.warcardgamecompose.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.NavEntry
import com.example.warcardgamecompose.game.ui.CPUGameScreen
import com.example.warcardgamecompose.ChooseGameModeScreen
import com.example.warcardgamecompose.LoginScreen
import com.example.warcardgamecompose.LoginViewModel
import com.example.warcardgamecompose.game.ui.MultiplayerGameScreen
import com.example.warcardgamecompose.ProfileScreen
import com.example.warcardgamecompose.game.ui.PvPGameScreen
import com.example.warcardgamecompose.RegisterScreen
import com.example.warcardgamecompose.auth.AuthStatus
import com.example.warcardgamecompose.auth.AuthUiState
import com.example.warcardgamecompose.auth.AuthViewModel
import com.example.warcardgamecompose.game.ui.ResultScreen
import com.example.warcardgamecompose.game.ui.WarScreen
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {

    val authViewModel: AuthViewModel = koinViewModel()
    val loginViewModel: LoginViewModel = koinViewModel()
    val uiState by authViewModel.uiState.collectAsStateWithLifecycle()
    val authStatus by authViewModel.authStatus.collectAsStateWithLifecycle()
    val backStack = rememberNavBackStack(LoginDestination)

    LaunchedEffect(authStatus) {
        if (authStatus == AuthStatus.LoggedIn) {
            loginViewModel.reset()
            backStack.clear()
            backStack.addLast(ChooseGameModeScreenDestination)
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.RegisterSuccess) {
            backStack.removeLastOrNull()
        }
    }
    NavDisplay(modifier = modifier,
        backStack = backStack,
        entryProvider = {key ->
            when(key) {
                is LoginDestination -> NavEntry(key) {
                    val state by loginViewModel.uiState.collectAsStateWithLifecycle()
                    LoginScreen(
                        state = state,
                        onLoginButtonClick = { authViewModel.login(state.email, state.password)},
                        onEmailChange = loginViewModel::onEmailChange,
                        onPasswordChange = loginViewModel::onPasswordChange,
                        onGuestButtonClick = {backStack.addLast(ChooseGameModeScreenDestination)},
                        onRegisterButtonClick = {backStack.addLast(RegisterDestination)},
                        onGoogleLoginClick = {authViewModel.loginWithGoogle()},
                        onFacebookLoginClick = {}
                    )

                }

                is CPUGameScreenDestination -> NavEntry(key) {
                    CPUGameScreen(onGameFinished = {winner ->
                        backStack.addLast(ResultScreenDestination(winner))

                    },onExitButtonClick =  { backStack.removeLastOrNull()},
                        onWarStarted = {backStack.addLast(WarScreenDestination)} )
                }
                is RegisterDestination -> NavEntry(key){
                    val loginViewModel: LoginViewModel = koinViewModel()
                    val state by loginViewModel.uiState.collectAsStateWithLifecycle()
                    RegisterScreen(
                        onUsernameChange = loginViewModel::onUsernameChange,
                        onEmailChange = loginViewModel::onEmailChange,
                        onPasswordChange = loginViewModel::onPasswordChange,
                        onRegisterButtonClick = {authViewModel.register(state.email, state.password) },
                        onExitButtonClick = {backStack.removeLastOrNull()},
                        state = state
                    )
                }

                is ChooseGameModeScreenDestination -> NavEntry(key) {
                    ChooseGameModeScreen(onExitButtonClick = {backStack.removeLastOrNull()},
                        onCPUButtonClick ={backStack.addLast(CPUGameScreenDestination)},
                        onPvpButtonClick = {backStack.addLast(PvPGameScreenDestination)},
                        onMultiplayerButtonClick = {backStack.addLast(MultiplayerScreenDestination)})


                }

                is MultiplayerScreenDestination -> NavEntry(key) {
                    MultiplayerGameScreen(
                        onDealButtonClick = {},
                        onExitButtonClick = {
                            backStack.removeLastOrNull()
                        })

                }

                is PvPGameScreenDestination -> NavEntry(key) {
                    PvPGameScreen(
                        onPlayerExitButtonClick = {
                            backStack.removeLastOrNull()
                        },
                        onPlayerDealButtonClick = {},
                        onOpponentExitButtonClick = {
                            backStack.removeLastOrNull()
                        },
                        onOpponentDealButtonClick = {}
                    )

                }

                is ProfileScreenDestination -> NavEntry(key) {
                    ProfileScreen()
                }

                is WarScreenDestination -> NavEntry(key) {
                    WarScreen(
                        onExitButtonClick ={backStack.removeLastOrNull()},
                        onWarFinished = {backStack.removeLastOrNull()}
                    )
                }

                is ResultScreenDestination -> NavEntry(key) {
                    ResultScreen(winner = key.winner,
                        onExitButtonClick = {
                            backStack.clear()
                            backStack.addLast(ChooseGameModeScreenDestination)
                        })
                }
                else -> error("Unknown_destination")
            }
        }
    )


}
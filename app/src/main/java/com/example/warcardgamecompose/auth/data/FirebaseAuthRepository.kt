package com.example.warcardgamecompose.auth.data

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.warcardgamecompose.R
import com.example.warcardgamecompose.auth.AuthRepository
import com.example.warcardgamecompose.auth.domain.SignInResult
import com.example.warcardgamecompose.auth.domain.UserData
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FacebookAuthProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirebaseAuthRepository(private val context: Context): AuthRepository {

    private val auth =  Firebase.auth
    private val credentialManager = CredentialManager.create(context)
    override suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    private val callbackManager = CallbackManager.Factory.create()

    override suspend fun loginWithGoogle(): SignInResult =
        withContext(Dispatchers.Main) {
            try {
                val googleIdOption = GetGoogleIdOption.Builder()
                    .setServerClientId(context.getString(R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                val result = credentialManager.getCredential(context, request)

                val googleCredential = GoogleIdTokenCredential.createFrom(result.credential.data)

                val firebaseCredential = GoogleAuthProvider.getCredential(googleCredential.idToken, null)

                val user = auth.signInWithCredential(firebaseCredential).await().user

                SignInResult(data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl =  photoUrl?.toString()
                    )
                }, errorMessage = null)
            } catch (exception: GetCredentialException) {
                SignInResult(data = null,
                    errorMessage = exception.message)
            } catch (exception: Exception) {
                SignInResult(
                    data = null,
                    errorMessage = exception.message
                )
            }
        }

    override suspend fun loginWithFacebook(activity: Activity): SignInResult =
        suspendCancellableCoroutine { cont ->
            Log.d("AUTH", "Starting Facebook login")
            LoginManager.getInstance()
                .logInWithReadPermissions(
                    activity, listOf("email", "public_profile")
                )
            LoginManager.getInstance()
                .registerCallback(
                    callbackManager,
                    object: FacebookCallback<LoginResult>{
                        override fun onCancel() {
                            cont.resume(
                                SignInResult(null, errorMessage = "Facebook login cancelled"
                            )
                            )
                        }

                        override fun onError(error: FacebookException) {
                            cont.resume(
                                SignInResult(
                                    null, error.message ?: "Facebook login error"
                                )
                            )
                        }

                        override fun onSuccess(result: LoginResult) {
                            val credential = FacebookAuthProvider.getCredential(
                                result.accessToken.token
                            )

                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    val user = auth.signInWithCredential(credential)
                                        .await()
                                        .user

                                    cont.resume(
                                        SignInResult(
                                            data = user?.run {
                                                UserData(
                                                    userId = uid,
                                                    username = displayName,
                                                    profilePictureUrl = photoUrl.toString()
                                                )
                                            }, errorMessage = null
                                        )
                                    )
                                }catch (exception: Exception) {
                                    cont.resume(SignInResult(
                                        data = null, errorMessage = exception.message
                                    ))
                                }
                            }
                        }

                    }
                )
        }



    override suspend fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override fun logout() {
        auth.signOut()
    }

    override fun getFacebookCallbackManager(): CallbackManager = callbackManager


    override fun getCurrentUser(): UserData? =
        auth.currentUser?.run {
        UserData(
                userId = uid,
                username = displayName,
                profilePictureUrl = photoUrl?.toString()
            )
        }
}
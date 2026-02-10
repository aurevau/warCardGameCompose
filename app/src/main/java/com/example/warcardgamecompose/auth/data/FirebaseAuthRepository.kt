package com.example.warcardgamecompose.auth.data

import android.content.Context
import android.content.Intent
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.warcardgamecompose.R
import com.example.warcardgamecompose.auth.AuthRepository
import com.example.warcardgamecompose.auth.domain.SignInResult
import com.example.warcardgamecompose.auth.domain.UserData
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseAuthRepository(private val context: Context): AuthRepository {

    private val auth =  Firebase.auth
    private val credentialManager = CredentialManager.create(context)
    override suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

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



    override suspend fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override fun logout() {
        auth.signOut()
    }

    override fun getCurrentUser(): UserData? =
        auth.currentUser?.run {
        UserData(
                userId = uid,
                username = displayName,
                profilePictureUrl = photoUrl?.toString()
            )
        }
}
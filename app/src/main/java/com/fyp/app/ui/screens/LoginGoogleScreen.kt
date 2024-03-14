package com.example.myapplication.domain.authentication.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fyp.app.R
import com.fyp.app.ui.theme.OneTapComposeTheme
import com.stevdzasan.onetap.GoogleUser
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun LoginGoogleScreen() {
    OneTapComposeTheme {
        val state = rememberOneTapSignInState()
        var user: GoogleUser? by remember { mutableStateOf(null) }
        OneTapSignInWithGoogle(
            state = state,
            clientId = R.string.web_client_id.toString(),
            rememberAccount = true,
            onTokenIdReceived = {
                user = getUserFromTokenId(tokenId = it)
                Log.d("MainActivity", user.toString())
            },
            onDialogDismissed = {
                Log.d("MainActivity", it)
            }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { state.open() }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (state.opened) {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Sign in")
                }
            }
        }
    }
}
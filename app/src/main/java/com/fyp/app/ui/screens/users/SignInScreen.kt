package com.fyp.app.ui.screens.users

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.R
import com.fyp.app.data.model.SignInState
import com.fyp.app.domain.authenticationGoogle.GoogleAuthUiClient
import com.fyp.app.ui.components.buttons.GoogleSignInButton
import com.google.android.gms.auth.api.identity.Identity
import androidx.lifecycle.lifecycleScope
import com.fyp.app.ui.screens.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Composable
@Destination
fun SignInScreen(navigator: DestinationsNavigator) {


    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "Login image",
            modifier = Modifier
                .fillMaxSize(0.3f)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape),
            contentScale = ContentScale.Crop

        )
        Text(
            text = "Welcome Back",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Login to your account")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email address") })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Password") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /*TODO*/ }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(18.dp))

        TextButton(onClick = { /*TODO*/ }) {
            Text("Don't have an account? Sign up")
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(text = "Or sign in with")

        Spacer(modifier = Modifier.height(4.dp))

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult(),
            onResult = { result ->
                if(result.resultCode == ComponentActivity.RESULT_OK) {
                    coroutineScope.launch {
                        val signInResult = googleAuthUiClient.signInWithIntent(
                            intent = result.data ?: return@launch
                        )
                        navigator.navigate(ProfileScreenDestination(googleAuthUiClient.getSignedInUser()))
                    }
                }
            }
        )
        GoogleSignInButton(onClick = {
            coroutineScope.launch {
                val signInIntentSender = googleAuthUiClient.signIn()
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        })
    }
}
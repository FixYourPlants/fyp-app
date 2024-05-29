package com.fyp.app.ui.screens.users

import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.R
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.data.api.responses.RegistrationRequest
import com.fyp.app.domain.authenticationGoogle.GoogleAuthUiClient
import com.fyp.app.ui.components.buttons.GoogleSignInButton
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.LoginScreenDestination
import com.fyp.app.ui.screens.destinations.ProfileScreenDestination
import com.fyp.app.ui.screens.destinations.SignInScreenDestination
import com.google.android.gms.auth.api.identity.Identity
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Composable
@Destination
fun SignInScreen(navigator: DestinationsNavigator) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var attemptRegister by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (attemptRegister) {
        LaunchedEffect(email, username, password, repeatPassword) {
            if (password != repeatPassword) {
                errorMessage = "Passwords do not match"
                attemptRegister = false
                return@LaunchedEffect
            }

            try {
                loading = true
                errorMessage = null
                val responseRegister = UserServiceImp.getInstance().registerUser(
                    RegistrationRequest(
                        email = email,
                        password1 = password,
                        password2 = repeatPassword,
                        username = username
                    )
                )
                // Guardar el email y el token del usuario en SharedPreferences
                UserServiceImp.getInstance().getUserIdByEmail(email)

                // Navegar a la pantalla principal
                navigator.navigate(HomeScreenDestination())
            } catch (e: Exception) {
                // Handle error
                errorMessage = e.message
                Log.e("SignInScreen", "Error signing up $e")
            } finally {
                loading = false
                attemptRegister = false
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Login image",
                modifier = Modifier
                    .requiredSize(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Welcome",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Create an account")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email address") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                label = { Text("Repeat password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    attemptRegister = true
                },
                enabled = !loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                } else {
                    Text("Sign up")
                }
            }

            errorMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(text = "Or sign in with")

            Spacer(modifier = Modifier.height(4.dp))

            val coroutineScope = rememberCoroutineScope()

            val googleAuthUiClient by lazy {
                GoogleAuthUiClient(
                    context = context,
                    oneTapClient = Identity.getSignInClient(context)
                )
            }

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

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navigator.navigate(LoginScreenDestination())
            }) {
                Text("Already have an account? Login")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


package com.fyp.app.ui.screens.users

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import com.fyp.app.data.api.responses.LoginRequest
import com.fyp.app.domain.authenticationGoogle.GoogleAuthUiClient
import com.fyp.app.ui.components.buttons.GoogleSignInButton
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.ProfileScreenDestination
import com.fyp.app.ui.screens.destinations.SignInScreenDestination
import com.fyp.app.utils.UserPreferences
import com.google.android.gms.auth.api.identity.Identity
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Composable
@RootNavGraph(start = true)
@Destination
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    // Mutable states for the input fields
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var attemptLogin by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (attemptLogin) {
        LaunchedEffect(email, password) {
            try {
                loading = true
                errorMessage = null
                val responseLogin = UserServiceImp.getInstance().loginUser(
                    LoginRequest(
                        email = email,
                        password = password,
                        username = ""
                    )
                )
                // Guardar el email y el token del usuario en SharedPreferences
                val responseId = UserServiceImp.getInstance().getUserIdByEmail(email)
                responseId["user_id"]?.let {
                    UserPreferences.saveUser(context, email, responseLogin.key, it)
                }

                // Navegar a la pantalla principal
                navigator.navigate(HomeScreenDestination())
            } catch (e: Exception) {
                // Handle error
                errorMessage = e.message
                Log.e("LoginScreen", "Error logging in $e")
            } finally {
                loading = false
                attemptLogin = false
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
                text = "Welcome Back",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Login to your account")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email address") },
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

            Button(
                onClick = {
                    attemptLogin = true
                },
                enabled = !loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                } else {
                    Text("Login")
                }
            }

            errorMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(18.dp))

            TextButton(onClick = {
                navigator.navigate(SignInScreenDestination())
            }) {
                Text("Don't have an account? Sign up")
            }
        }
    }
}







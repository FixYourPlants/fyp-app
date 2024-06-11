package com.fyp.app.ui.screens.users

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.data.api.responses.RegistrationRequest
import com.fyp.app.ui.components.ErrorMessage
import com.fyp.app.ui.components.HeaderInit
import com.fyp.app.ui.components.InputField
import com.fyp.app.ui.components.LogoInit
import com.fyp.app.ui.components.buttons.ActionButton
import com.fyp.app.ui.components.buttons.ButtonLink
import com.fyp.app.ui.components.buttons.GoogleSignInButton
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun SignInScreen(navigator: DestinationsNavigator) {
    // State variables for input fields, loading state, error message, and registration attempt
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var attemptRegister by remember { mutableStateOf(false) }
    val showError = remember { mutableStateOf(false) }

    // Handle registration attempt
    if (attemptRegister) {
        LaunchedEffect(email, username, password, repeatPassword) {
            // Check if passwords match
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
                // Save user email and token in SharedPreferences
                UserServiceImp.getInstance().getUserIdByUsername(email)
                // Navigate to the home screen
                navigator.navigate(HomeScreenDestination())
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("SignInScreen", "Error signing up $e")
            } finally {
                loading = false
                attemptRegister = false
            }
        }
    }

    // UI layout
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            LogoInit(onClickLogo = { navigator.navigate(HomeScreenDestination()) })
            HeaderInit(text = "Welcome")
            Text(text = "Create an account")
            Spacer(modifier = Modifier.height(16.dp))
            InputField(value = email, onValueChange = { email = it }, label = "Email address")
            Spacer(modifier = Modifier.height(16.dp))
            InputField(value = username, onValueChange = { username = it }, label = "Username")
            Spacer(modifier = Modifier.height(16.dp))
            InputField(value = password, onValueChange = { password = it }, label = "Password", isPassword = true)
            Spacer(modifier = Modifier.height(16.dp))
            InputField(value = repeatPassword, onValueChange = { repeatPassword = it }, label = "Repeat password", isPassword = true)
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "Sign up", onClick = { attemptRegister = true }, isLoading = loading)
            errorMessage?.let { showError.value = true; ErrorMessage(it, showError) }
            Spacer(modifier = Modifier.height(18.dp))
            ButtonLink(text = "Already have an account? Login") {
                navigator.navigate(LoginScreenDestination())
            }
            Spacer(modifier = Modifier.height(16.dp))
            GoogleSignInButton(onClick = { navigator.navigate(HomeScreenDestination()) })
        }
    }
}


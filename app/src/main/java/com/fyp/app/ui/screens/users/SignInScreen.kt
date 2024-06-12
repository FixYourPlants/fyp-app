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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.data.api.responses.RegistrationRequest
import com.fyp.app.ui.components.ErrorMessage
import com.fyp.app.ui.components.HeaderInit
import com.fyp.app.ui.components.InputField
import com.fyp.app.ui.components.LogoInit
import com.fyp.app.ui.components.TextFieldError
import com.fyp.app.ui.components.ValidatedTextFieldLoginRegister
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
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var attemptRegister by remember { mutableStateOf(false) }
    val showError = remember { mutableStateOf(false) }
    val emailErrors = remember { mutableStateOf(listOf<TextFieldError>()) }
    val usernameErrors = remember { mutableStateOf(listOf<TextFieldError>()) }
    val passwordErrors = remember { mutableStateOf(listOf<TextFieldError>()) }
    val repeatPasswordErrors = remember { mutableStateOf(listOf<TextFieldError>()) }

    if (attemptRegister) {
        LaunchedEffect(email, username, password, repeatPassword) {
            // Reset errors
            emailErrors.value = listOf()
            usernameErrors.value = listOf()
            passwordErrors.value = listOf()
            repeatPasswordErrors.value = listOf()

            // Check if passwords match
            if (password != repeatPassword) {
                repeatPasswordErrors.value = listOf(TextFieldError(true, "Passwords do not match"))
                errorMessage = null
                attemptRegister = false
                return@LaunchedEffect
            }

            try {
                loading = true
                errorMessage = null
                val responseRegister = UserServiceImp.getInstance().registerUser(
                    RegistrationRequest(
                        email = email,
                        password = password,
                        password2 = repeatPassword,
                        username = username
                    )
                )

                Log.d("SignInScreen", "responseRegister: $responseRegister")

                // Save user email and token in SharedPreferences
                UserServiceImp.getInstance().getUserIdByUsername(username)
                // Navigate to the home screen
                navigator.navigate(HomeScreenDestination())
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("SignInScreen", "Error signing up $e")
                if (e.message?.contains("email") == true || e.message?.contains("400") == true) {
                    emailErrors.value = listOf(TextFieldError(true, "Invalid email address"))
                    errorMessage = null
                }
                if (e.message?.contains("username") == true || e.message?.contains("400") == true) {
                    usernameErrors.value = listOf(TextFieldError(true, "Invalid username"))
                    errorMessage = null
                }
                if (e.message?.contains("password") == true || e.message?.contains("400") == true) {
                    passwordErrors.value = listOf(TextFieldError(true, "Invalid password"))
                    errorMessage = null
                }
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
            LogoInit(onClickLogo = { navigator.navigate(HomeScreenDestination()) })
            HeaderInit(text = "Welcome")
            Text(text = "Create an account")
            Spacer(modifier = Modifier.height(16.dp))
            ValidatedTextFieldLoginRegister(
                value = email,
                onValueChange = { email = it },
                label = "Email address",
                errors = emailErrors.value,
                imeAction = ImeAction.Next,
                fraction = 0.7f
            )
            Spacer(modifier = Modifier.height(16.dp))
            ValidatedTextFieldLoginRegister(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                errors = usernameErrors.value,
                imeAction = ImeAction.Next,
                fraction = 0.7f
            )
            Spacer(modifier = Modifier.height(16.dp))
            ValidatedTextFieldLoginRegister(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                errors = passwordErrors.value,
                imeAction = ImeAction.Next,
                fraction = 0.7f,
                secret = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            ValidatedTextFieldLoginRegister(
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                label = "Repeat password",
                errors = repeatPasswordErrors.value,
                imeAction = ImeAction.Done,
                fraction = 0.7f,
                secret = true
            )
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



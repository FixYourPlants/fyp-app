package com.fyp.app.ui.screens.users

import TokenManager
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
import com.fyp.app.data.api.TokenServiceImp
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.ui.components.ErrorMessage
import com.fyp.app.ui.components.HeaderInit
import com.fyp.app.ui.components.InputField
import com.fyp.app.ui.components.LogoInit
import com.fyp.app.ui.components.buttons.ActionButton
import com.fyp.app.ui.components.buttons.ButtonLink
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.SignInScreenDestination
import com.fyp.app.utils.UserPreferencesImp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var attemptLogin by remember { mutableStateOf(false) }
    val showError = remember { mutableStateOf(false) }

    if (attemptLogin) {
        LaunchedEffect(username, password) {
            try {
                loading = true
                errorMessage = null

                val responseId = UserServiceImp.getInstance().getUserIdByUsername(username)

                val tokens = TokenServiceImp.getInstance().getSimpleToken(
                    mapOf(
                        "username" to username,
                        "password" to password
                    )
                )

                responseId["user_id"]?.let {
                    UserPreferencesImp.initialize(username, tokens["refresh"].toString(), tokens["access"].toString(), it)
                }

                navigator.navigate(HomeScreenDestination())
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("LoginScreen", "Error logging in $e")
            } finally {
                loading = false
                attemptLogin = false
                TokenManager.resetAllServices()
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
            HeaderInit(text = "Welcome Back")
            Text(text = "Login to your account")
            Spacer(modifier = Modifier.height(16.dp))
            InputField(value = username, onValueChange = { username = it }, label = "Username")
            Spacer(modifier = Modifier.height(16.dp))
            InputField(value = password, onValueChange = { password = it }, label = "Password", isPassword = true)
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "Login", onClick = { attemptLogin = true }, isLoading = loading)
            errorMessage?.let { showError.value = true;ErrorMessage(it, showError) }
            Spacer(modifier = Modifier.height(18.dp))
            ButtonLink(text = "Don't have an account? Sign up") {
                navigator.navigate(SignInScreenDestination())
            }
        }
    }
}







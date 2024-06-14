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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fyp.app.BuildConfig
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.ui.components.ErrorMessage
import com.fyp.app.ui.components.HeaderInit
import com.fyp.app.ui.components.LogoInit
import com.fyp.app.ui.components.TextFieldError
import com.fyp.app.ui.components.ValidatedTextFieldLoginRegister
import com.fyp.app.ui.components.buttons.ActionButton
import com.fyp.app.ui.components.buttons.ButtonLink
import com.fyp.app.ui.components.buttons.ClickableUrlText
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.SignInScreenDestination
import com.fyp.app.utils.UserPreferencesImp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

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
    val usernameErrors = remember { mutableStateOf(listOf<TextFieldError>()) }
    val passwordErrors = remember { mutableStateOf(listOf<TextFieldError>()) }
    val emailErrors = remember { mutableStateOf(listOf<TextFieldError>()) }


    if (attemptLogin) {
        LaunchedEffect(username, password) {
            try {
                loading = true
                errorMessage = null

                // Reset errors
                usernameErrors.value = listOf()
                passwordErrors.value = listOf()
                emailErrors.value = listOf()

                val responseId = UserServiceImp.getInstance().getUserIdByUsername(username)

                val tokens = UserServiceImp.getInstance().loginUserVerified(
                    mapOf(
                        "username" to username,
                        "password" to password
                    )
                )
                responseId["user_id"]?.let {
                    UserPreferencesImp.initialize(username, tokens["refresh"].toString(), tokens["access"].toString(), it)
                }

                navigator.navigate(HomeScreenDestination())
            } catch (e: HttpException) {
                errorMessage = e.message
                val errorResponseBody: ResponseBody? = e.response()?.errorBody()
                if (errorResponseBody != null) {
                    val errorResponse = errorResponseBody.string()
                    val errorJson = JSONObject(errorResponse)
                    Log.e("VIEJO SABROSO", errorJson.toString())
                    if (errorJson.has("username") || e.message?.contains("404") == true){
                        usernameErrors.value = listOf(TextFieldError(true, "Nombre de usuario inválido"))
                        errorMessage = null
                    }
                    if (errorJson.has("password") || e.message?.contains("401") == true) {
                        passwordErrors.value = listOf(TextFieldError(true, "Contraseña inválida"))
                        errorMessage =  null
                    }
                    if (errorJson.has("email")) {
                        emailErrors.value = listOf(TextFieldError(true, "Su cuenta no ha realizado la validación por correo."))
                        errorMessage =  null
                    }
                }
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
            HeaderInit(text = "Bienvenido de Nuevo")
            Text(text = "Inicia sesión en tu cuenta")
            Spacer(modifier = Modifier.height(16.dp))
            ValidatedTextFieldLoginRegister(
                value = username,
                onValueChange = { username = it },
                label = "Nombre de usuario",
                errors = usernameErrors.value,
                imeAction = ImeAction.Next,
                fraction = 0.7f
            )
            Spacer(modifier = Modifier.height(1.dp))
            ValidatedTextFieldLoginRegister(
                value = password,
                onValueChange = { password = it },
                label = "Contraseña",
                errors = passwordErrors.value,
                imeAction = ImeAction.Done,
                fraction = 0.7f,
                secret = true
            )
            Spacer(modifier = Modifier.height(18.dp))
            ActionButton(text = "Iniciar sesión", onClick = { attemptLogin = true }, isLoading = loading)
            if(emailErrors.value.isNotEmpty()){
                showError.value = true
                ErrorMessage(emailErrors.value[0].errorMessage,showError)
            }
            errorMessage?.let { showError.value = true; ErrorMessage(it, showError) }
            Spacer(modifier = Modifier.height(18.dp))
            ButtonLink(text = "¿No tienes una cuenta? Regístrate") {
                navigator.navigate(SignInScreenDestination())
            }
            ClickableUrlText(BuildConfig.BACKEND_URL + "/api/v1/password-reset/","¿Has olvidado tu contraseña?")
        }
    }
}









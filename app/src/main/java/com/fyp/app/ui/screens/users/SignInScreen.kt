package com.fyp.app.ui.screens.users

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.data.model.RegistrationRequest
import com.fyp.app.domain.authenticationGoogle.GoogleAuthUiClient
import com.fyp.app.ui.components.ErrorMessage
import com.fyp.app.ui.components.HeaderInit
import com.fyp.app.ui.components.LogoInit
import com.fyp.app.ui.components.TextFieldError
import com.fyp.app.ui.components.ValidatedTextFieldLoginRegister
import com.fyp.app.ui.components.buttons.ActionButton
import com.fyp.app.ui.components.buttons.ButtonLink
import com.fyp.app.ui.components.buttons.GoogleSignInButton
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.LoginScreenDestination
import com.google.android.gms.auth.api.identity.Identity
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

@Composable
@Destination
fun SignInScreen(navigator: DestinationsNavigator) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var passwordGoogle by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var attemptRegister by remember { mutableStateOf(false) }
    val showError = remember { mutableStateOf(false) }
    val emailErrors = remember { mutableStateOf(listOf<TextFieldError>()) }
    val usernameErrors = remember { mutableStateOf(listOf<TextFieldError>()) }
    val passwordErrors = remember { mutableStateOf(listOf<TextFieldError>()) }
    val repeatPasswordErrors = remember { mutableStateOf(listOf<TextFieldError>()) }

    val context = LocalContext.current
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
                    val map = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    val responseRegister = UserServiceImp.getInstance().registerUser(
                        RegistrationRequest(
                            email = map["email"]!!,
                            password = map["id"]!!,
                            password2 = map["id"]!!,
                            googleAccount = true,
                            username = map["email"]!!.replace("@",".")
                        )
                    )
                    Log.d("SignInScreen Google", "responseRegister: $responseRegister")
                    UserServiceImp.getInstance().getUserIdByUsername(map["email"]!!.replace("@","."))
                    navigator.navigate(HomeScreenDestination())
                    loading = false
                    attemptRegister = false
                }
            }
        }
    )

    if (attemptRegister) {
        LaunchedEffect(email, username, password, repeatPassword) {
            // Reset errors
            emailErrors.value = listOf()
            usernameErrors.value = listOf()
            passwordErrors.value = listOf()
            repeatPasswordErrors.value = listOf()

            // Check if passwords match
            if (password != repeatPassword) {
                repeatPasswordErrors.value = listOf(TextFieldError(true, "Las contraseñas no coinciden"))
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
                        username = username,
                        googleAccount = false
                    )
                )
                Log.d("SignInScreen", "responseRegister: $responseRegister")

                // Save user email and token in SharedPreferences
                UserServiceImp.getInstance().getUserIdByUsername(username)
                // Navigate to the home screen
                navigator.navigate(HomeScreenDestination())
            } catch (e: HttpException) {
                errorMessage = e.message
                val errorResponseBody: ResponseBody? = e.response()?.errorBody()
                if (errorResponseBody != null) {
                    val errorResponse = errorResponseBody.string()
                    val errorJson = JSONObject(errorResponse)

                    Log.e("SignInScreen", "Error registrándose $e")
                    if (errorJson.has("email")) {
                        emailErrors.value = listOf(TextFieldError(true, errorJson.getJSONArray("email").get(0).toString().capitalize()))
                        errorMessage = null
                    }
                    if (errorJson.has("username")) {
                        usernameErrors.value = listOf(TextFieldError(true, errorJson.getJSONArray("username").get(0).toString().capitalize()))
                        errorMessage = null
                    }
                    if (errorJson.has("password")) {
                        passwordErrors.value = listOf(TextFieldError(true, errorJson.getJSONArray("password").get(0).toString().capitalize()))
                        errorMessage = null
                    }
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
            HeaderInit(text = "Bienvenido")
            Text(text = "Crea una cuenta")
            Spacer(modifier = Modifier.height(16.dp))
            ValidatedTextFieldLoginRegister(
                value = email,
                onValueChange = { email = it },
                label = "Dirección de correo electrónico",
                errors = emailErrors.value,
                imeAction = ImeAction.Next,
                fraction = 0.7f
            )
            Spacer(modifier = Modifier.height(16.dp))
            ValidatedTextFieldLoginRegister(
                value = username,
                onValueChange = { username = it },
                label = "Nombre de usuario",
                errors = usernameErrors.value,
                imeAction = ImeAction.Next,
                fraction = 0.7f
            )
            Spacer(modifier = Modifier.height(16.dp))
            ValidatedTextFieldLoginRegister(
                value = password,
                onValueChange = { password = it },
                label = "Contraseña",
                errors = passwordErrors.value,
                imeAction = ImeAction.Next,
                fraction = 0.7f,
                secret = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            ValidatedTextFieldLoginRegister(
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                label = "Repetir contraseña",
                errors = repeatPasswordErrors.value,
                imeAction = ImeAction.Done,
                fraction = 0.7f,
                secret = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "Registrarse", onClick = { attemptRegister = true }, isLoading = loading)
            errorMessage?.let { showError.value = true; ErrorMessage(it, showError) }
            Spacer(modifier = Modifier.height(18.dp))
            ButtonLink(text = "¿Ya tienes una cuenta? Inicia sesión") {
                navigator.navigate(LoginScreenDestination())
            }
            Spacer(modifier = Modifier.height(16.dp))
            GoogleSignInButton(onClick = {
                coroutineScope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            }, text = "Sign In with Google")
        }
    }
}




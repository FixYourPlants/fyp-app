package com.fyp.app.ui.screens.users

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fyp.app.BuildConfig
import com.fyp.app.R
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.data.model.db.User
import com.fyp.app.ui.components.TextFieldError
import com.fyp.app.ui.components.ValidatedTextField
import com.fyp.app.ui.screens.destinations.CameraScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.fyp.app.viewmodel.CameraViewModel
import com.fyp.app.viewmodel.CameraViewModelImp
import com.fyp.app.viewmodel.toMultipartBodyPart
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
@Destination
fun UserEditScreen(
    navigator: DestinationsNavigator,
    user: User,
) {
    val cameraViewModel: CameraViewModel = CameraViewModelImp.getInstance()
    var updatedUsername by remember { mutableStateOf(user.username) }
    var updatedFirstName by remember { mutableStateOf(user.firstName) }
    var updatedLastName by remember { mutableStateOf(user.lastName) }
    var updatedEmail by remember { mutableStateOf(user.email) }
    var updatedAboutMe by remember { mutableStateOf(user.aboutMe) }
    val updatedImages by cameraViewModel.bitmaps.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    var usernameError by remember { mutableStateOf("") }
    var firstNameError by remember { mutableStateOf("") }
    var lastNameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var aboutMeError by remember { mutableStateOf("") }

    val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
    val nameRegex = "^[a-zA-Z\\s]+$".toRegex()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(2.dp)
            .background(Color(0xFF000500))
            .padding(2.dp)
            .background(Color(0xFF4CAF50))
            .padding(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = MaterialTheme.shapes.medium)
                    .clickable {
                        navigator.navigate(CameraScreenDestination)
                    }
            ) {
                if (updatedImages.isNotEmpty()) {
                    Image(
                        bitmap = updatedImages.last().asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(BuildConfig.BACKEND_URL + user.imageUrl)
                            .placeholder(R.drawable.down)
                            .error(R.drawable.down_down)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ValidatedTextField(
                value = updatedUsername,
                onValueChange = {
                    updatedUsername = it
                    usernameError = when {
                        it.isEmpty() -> "Username cannot be empty"
                        it.length < 3 -> "Username must be at least 3 characters"
                        else -> ""
                    }
                },
                label = "Username",
                modifier = Modifier.fillMaxWidth(),
                errors = listOf(TextFieldError(usernameError.isNotEmpty(), usernameError)),
                imeAction = ImeAction.Next
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ValidatedTextField(
                value = updatedFirstName,
                onValueChange = {
                    updatedFirstName = it
                    firstNameError = when {
                        it.isEmpty() -> "First name cannot be empty"
                        !nameRegex.matches(it) -> "First name can only contain letters"
                        else -> ""
                    }
                },
                label = "First Name",
                modifier = Modifier.fillMaxWidth(),
                errors = listOf(TextFieldError(firstNameError.isNotEmpty(), firstNameError)),
                imeAction = ImeAction.Next
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ValidatedTextField(
                value = updatedLastName,
                onValueChange = {
                    updatedLastName = it
                    lastNameError = when {
                        it.isEmpty() -> "Last name cannot be empty"
                        !nameRegex.matches(it) -> "Last name can only contain letters"
                        else -> ""
                    }
                },
                label = "Last Name",
                modifier = Modifier.fillMaxWidth(),
                errors = listOf(TextFieldError(lastNameError.isNotEmpty(), lastNameError)),
                imeAction = ImeAction.Next
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ValidatedTextField(
                value = updatedEmail,
                onValueChange = {
                    updatedEmail = it
                    emailError = when {
                        it.isEmpty() -> "Email cannot be empty"
                        !emailRegex.matches(it) -> "Invalid email format"
                        else -> ""
                    }
                },
                label = "Email",
                modifier = Modifier.fillMaxWidth(),
                errors = listOf(TextFieldError(emailError.isNotEmpty(), emailError)),
                imeAction = ImeAction.Next
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ValidatedTextField(
                value = updatedAboutMe,
                onValueChange = {
                    updatedAboutMe = it
                    aboutMeError = when {
                        it.isEmpty() -> "About Me cannot be empty"
                        it.length < 10 -> "About Me must be at least 10 characters"
                        else -> ""
                    }
                },
                label = "About Me",
                modifier = Modifier.fillMaxWidth(),
                errors = listOf(TextFieldError(aboutMeError.isNotEmpty(), aboutMeError)),
                imeAction = ImeAction.Done,
                minLines = 20
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (updatedUsername.isEmpty()) usernameError = "Username cannot be empty"
                    if (updatedFirstName.isEmpty()) firstNameError = "First name cannot be empty"
                    if (updatedLastName.isEmpty()) lastNameError = "Last name cannot be empty"
                    if (updatedEmail.isEmpty()) emailError = "Email cannot be empty"
                    if (updatedAboutMe.isEmpty()) aboutMeError = "About Me cannot be empty"

                    if (usernameError.isEmpty() && firstNameError.isEmpty() && lastNameError.isEmpty() && emailError.isEmpty() && aboutMeError.isEmpty()) {
                        coroutineScope.launch {
                            try {
                                val usernamePart = updatedUsername.toRequestBody("text/plain".toMediaTypeOrNull())
                                val firstNamePart = updatedFirstName.toRequestBody("text/plain".toMediaTypeOrNull())
                                val lastNamePart = updatedLastName.toRequestBody("text/plain".toMediaTypeOrNull())
                                val emailPart = updatedEmail.toRequestBody("text/plain".toMediaTypeOrNull())
                                val aboutMePart = updatedAboutMe.toRequestBody("text/plain".toMediaTypeOrNull())
                                val imagePart = cameraViewModel.getSelectedBitmap()
                                    ?.toMultipartBodyPart("image")

                                UserServiceImp.getInstance().updateUser(user.id, usernamePart , firstNamePart, lastNamePart, emailPart, aboutMePart, imagePart)
                            } catch (e: Exception) {
                                Log.e("UserEditScreen", "Error updating user", e)
                            }
                            Log.d("UserEditScreen", "User updated")
                        }

                        // Implement your onUpdateUser(updatedUser) logic here
                        navigator.navigate(UserDetailsScreenDestination(user))
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Save",
                    color = Color(0xFF4CAF50)
                )
            }
        }
    }
}




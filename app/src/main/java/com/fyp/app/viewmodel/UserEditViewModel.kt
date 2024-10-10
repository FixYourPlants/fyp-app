package com.fyp.app.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.input.ImeAction
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.data.model.db.User
import com.fyp.app.utils.ValidatedTextFieldState
import com.fyp.app.viewmodel.camera.CameraViewModel
import com.fyp.app.viewmodel.camera.UserCameraViewModelImp
import com.fyp.app.viewmodel.camera.toMultipartBodyPart
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class UserEditViewModel(user: User) {
    val cameraViewModel: CameraViewModel = UserCameraViewModelImp.getInstance()
    val user = user

    // Use SnapshotStateList for observable state
    var fields = mutableStateListOf(
        ValidatedTextFieldState(user.username, "Username", ImeAction.Next, 1) { value ->
            updateField(0, value)
            validateField(0, value)
        },
        ValidatedTextFieldState(user.firstName, "First Name", ImeAction.Next, 1) { value ->
            updateField(1, value)
            validateField(1, value)
        },
        ValidatedTextFieldState(user.lastName, "Last Name", ImeAction.Next, 1) { value ->
            updateField(2, value)
            validateField(2, value)
        },
        ValidatedTextFieldState(user.email, "Email", ImeAction.Next, 1) { value ->
            updateField(3, value)
            validateField(3, value)
        },
        ValidatedTextFieldState(if(user.aboutMe != null) user.aboutMe else "", "About Me", ImeAction.Done, 20) { value ->
            updateField(4, value)
            validateField(4, value)
        }
    )
    var errors = mutableStateListOf("", "", "", "", "")

    suspend fun onSaveClick(onSuccess: (User) -> Unit) {
        if (errors.any { it.isNotEmpty() }) return

        try {
            val usernamePart = fields[0].value.toRequestBody("text/plain".toMediaTypeOrNull())
            val firstNamePart = fields[1].value.toRequestBody("text/plain".toMediaTypeOrNull())
            Log.d("UserEditViewModel", "firstNamePart: $firstNamePart")
            val lastNamePart = fields[2].value.toRequestBody("text/plain".toMediaTypeOrNull())
            val emailPart = fields[3].value.toRequestBody("text/plain".toMediaTypeOrNull())
            val aboutMePart = fields[4].value.toRequestBody("text/plain".toMediaTypeOrNull())
            val imagePart = cameraViewModel.getSelectedBitmap()?.toMultipartBodyPart("image")

            UserServiceImp.getInstance().updateUser(user.id, usernamePart, firstNamePart, lastNamePart, emailPart, aboutMePart, imagePart)
            val userUpdated = UserServiceImp.getInstance().getLoggedInUser()
            onSuccess(userUpdated)
        } catch (e: Exception) {
            Log.e("UserEditScreen", "Error updating user", e)
        }
    }

    private fun updateField(index: Int, value: String) {
        fields[index] = fields[index].copy(value = value)
    }

    private fun validateField(index: Int, value: String) {
        errors[index] = when (index) {
            0 -> when {
                value.isEmpty() -> "El nombre de usuario no puede estar vacío"
                value.length > 20 -> "El nombre de usuario no puede tener más de 20 caracteres"
                !value.matches(Regex("[a-zA-Z0-9]+")) -> "El nombre de usuario solo puede contener caracteres alfanuméricos"
                else -> ""
            }
            1 -> when {
                value.isEmpty() -> "El nombre no puede estar vacío"
                value.length > 50 -> "El nombre no puede tener más de 50 caracteres"
                else -> ""
            }
            2 -> when {
                value.isEmpty() -> "El apellido no puede estar vacío"
                value.length > 50 -> "El apellido no puede tener más de 50 caracteres"
                else -> ""
            }
            3 -> when {
                value.isEmpty() -> "El correo electrónico no puede estar vacío"
                !isValidEmail(value) -> "El correo electrónico no es válido"
                else -> ""
            }
            4 -> when {
                value.length < 10 -> "La descripción debe tener al menos 10 caracteres"
                else -> ""
            }
            else -> ""
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}


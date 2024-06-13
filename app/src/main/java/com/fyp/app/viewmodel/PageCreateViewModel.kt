package com.fyp.app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.input.ImeAction
import com.fyp.app.data.api.PageServiceImp
import com.fyp.app.data.model.db.Diary
import com.fyp.app.utils.ValidatedTextFieldState
import com.fyp.app.viewmodel.camera.CameraViewModel
import com.fyp.app.viewmodel.camera.PageCameraViewModelImp
import com.fyp.app.viewmodel.camera.toMultipartBodyPart
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PageCreateViewModel(diary: Diary) {
    val diary = diary
    val cameraViewModel: CameraViewModel = PageCameraViewModelImp.getInstance()

    // Use SnapshotStateList for observable state
    var fields = mutableStateListOf(
        ValidatedTextFieldState("", "Título", ImeAction.Next, 1) { value ->
            updateField(0, value)
            validateField(0, value)
        },
        ValidatedTextFieldState("", "Contenido", ImeAction.Done, 20) { value ->
            updateField(1, value)
            validateField(1, value)
        }
    )
    var errors = mutableStateListOf("", "")

    val today = Calendar.getInstance().time
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val formattedDate = dateFormatter.format(today)


    suspend fun onSaveClick(onSuccess: () -> Unit) {
        try {
            val titlePart = fields[0].value.toRequestBody("text/plain".toMediaTypeOrNull())
            val contentPart = fields[1].value.toRequestBody("text/plain".toMediaTypeOrNull())
            val diaryPart = this.diary.id.toRequestBody("text/plain".toMediaTypeOrNull())
            val imagePart = cameraViewModel.getSelectedBitmap()?.toMultipartBodyPart("image")

            PageServiceImp.getInstance().addPage(titlePart, contentPart, imagePart, diaryPart)
            onSuccess()
        } catch (e: Exception) {
            Log.e("PageCreateScreen", "Error creating page", e)
        }
    }

    private fun updateField(index: Int, value: String) {
        fields[index] = fields[index].copy(value = value)
    }

    private fun validateField(index: Int, value: String) {
        errors[index] = when (index) {
            0 -> when {
                value.isEmpty() -> "El título no puede estar vacío"
                value.length > 20 -> "El título no puede tener más de 20 caracteres"
                else -> ""
            }

            1 -> when {
                value.isEmpty() -> "El contenido no puede estar vacío"
                value.length < 10 -> "El contenido debe tener al menos 10 caracteres"
                else -> ""
            }
            else -> ""
        }
    }
}

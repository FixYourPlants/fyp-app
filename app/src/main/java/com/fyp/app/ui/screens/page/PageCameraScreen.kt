package com.fyp.app.ui.screens.page

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.fyp.app.data.api.PageServiceImp
import com.fyp.app.data.model.db.Diary
import com.fyp.app.ui.components.Camera
import com.fyp.app.viewmodel.toMultipartBodyPart
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
@Destination
fun PageCameraScreen(
    navigator: DestinationsNavigator,
    title: String,
    content: String,
    diary: Diary
) {
    val pageService = remember { PageServiceImp.getInstance() }
    val coroutineScope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    fun savePage(bitmap: Bitmap) {
        isLoading = true
        coroutineScope.launch {
            try {
                // Convertir el bitmap a MultipartBody.Part
                val imagePart = bitmap.toMultipartBodyPart("image")

                // Crear los RequestBody para el título, contenido y diario
                val titlePart = title.toRequestBody("text/plain".toMediaTypeOrNull())
                val contentPart = content.toRequestBody("text/plain".toMediaTypeOrNull())
                val diaryPart = diary.id.toRequestBody("text/plain".toMediaTypeOrNull()) // Reemplaza con el ID real del diario

                // Llamar al servicio para crear la página
                Log.d("PageCameraScreen", "Creating page...")
                pageService.addPage(titlePart, contentPart, imagePart, diaryPart)
                Log.d("PageCameraScreen", "Page created")
            } catch (e: Exception) {
                // Manejar el error
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    Camera { savePage(it) }

    if (isLoading) {
        CircularProgressIndicator()
    }
}

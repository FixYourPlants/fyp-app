package com.fyp.app.ui.screens.page

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.fyp.app.data.api.PageServiceImp
import com.fyp.app.data.model.db.Diary
import com.fyp.app.data.model.db.Page
import com.fyp.app.ui.screens.destinations.PageListScreenDestination
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@Composable
@Destination
fun PageCreateScreen(navigator: DestinationsNavigator, diary: Diary) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // URI para almacenar la imagen capturada
    val capturedImageUri = remember { mutableStateOf<Uri?>(null) }

    // Crear un lanzador para la cámara
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            selectedFileUri = capturedImageUri.value
        }
    }

    // Crear un archivo temporal para almacenar la imagen
    fun createImageFile(): Uri {
        val file = File(context.cacheDir, "captured_image.jpg")
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Contenido") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        Button(
            onClick = {
                capturedImageUri.value = createImageFile()
                cameraLauncher.launch(capturedImageUri.value)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Capturar Imagen")
        }
        selectedFileUri?.let { uri ->
            Text("Imagen Seleccionada: $uri", modifier = Modifier.padding(top = 8.dp))
        }
        Button(
            onClick = {
                val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                val newPage = Page(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    content = content,
                    createdAt = today,
                    imageUrl = selectedFileUri.toString(),
                    diary = diary
                )
                scope.launch(Dispatchers.IO) {
                    try {
                        val titlePart = title.toRequestBody("text/plain".toMediaTypeOrNull())
                        val contentPart = content.toRequestBody("text/plain".toMediaTypeOrNull())
                        val diaryPart = Gson().toJson(diary).toRequestBody("application/json".toMediaTypeOrNull())

                        val imagePart = selectedFileUri?.let {
                            val inputStream = context.contentResolver.openInputStream(it)
                            val file = File(context.cacheDir, "temp_image")
                            file.outputStream().use { outputStream ->
                                inputStream?.copyTo(outputStream)
                            }
                            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                            MultipartBody.Part.createFormData("image", file.name, requestFile)
                        }

                        PageServiceImp.getInstance().addPage(titlePart, contentPart, imagePart, diaryPart)
                        withContext(Dispatchers.Main) {
                            navigator.navigate(PageListScreenDestination(diary))
                        }
                    } catch (e: Exception) {
                        Log.e("CreatePageScreen", "Error creating page", e)
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Crear Página")
        }
    }
}


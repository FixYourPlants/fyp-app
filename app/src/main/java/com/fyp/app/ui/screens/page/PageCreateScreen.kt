package com.fyp.app.ui.screens.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.data.api.PageServiceImp
import com.fyp.app.data.model.db.Diary
import com.fyp.app.ui.components.TextFieldError
import com.fyp.app.ui.components.ValidatedTextField
import com.fyp.app.ui.components.buttons.DefaultButton
import com.fyp.app.ui.screens.destinations.CameraScreenDestination
import com.fyp.app.ui.screens.destinations.PageDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.PageListScreenDestination
import com.fyp.app.viewmodel.CameraViewModel
import com.fyp.app.viewmodel.CameraViewModelImp
import com.fyp.app.viewmodel.toMultipartBodyPart
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
@Destination
fun PageCreateScreen(
    navigator: DestinationsNavigator,
    diary: Diary,
) {
    val cameraViewModel: CameraViewModel = CameraViewModelImp.getInstance()
    var updatedTitle by remember { mutableStateOf("") }
    var updatedContent by remember { mutableStateOf("") }
    val updatedImages by cameraViewModel.bitmaps.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    var titleError by remember { mutableStateOf("") }
    var contentError by remember { mutableStateOf("") }

    val titleRegex = "^[a-zA-Z\\s]+$".toRegex()

    val today = Calendar.getInstance().time
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val formattedDate = dateFormatter.format(today)


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
            Text(
                text = "Crear página en ${diary.title.lowercase()} el $formattedDate",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = MaterialTheme.shapes.medium)
                    .clickable {
                        navigator.navigate(CameraScreenDestination)
                    }
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                if (updatedImages.isNotEmpty()) {
                    Image(
                        bitmap = updatedImages.last().asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(text = "Añadir imagen", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.align(Alignment.TopStart).padding(8.dp))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ValidatedTextField(
                value = updatedTitle,
                onValueChange = {
                    updatedTitle = it
                    titleError = when {
                        it.isEmpty() -> "El título no puede estar vacío"
                        !titleRegex.matches(it) -> "El título solo puede contener letras"
                        else -> ""
                    }
                },
                label = "Título",
                modifier = Modifier.fillMaxWidth(),
                errors = listOf(TextFieldError(titleError.isNotEmpty(), titleError)),
                imeAction = ImeAction.Next
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ValidatedTextField(
                value = updatedContent,
                onValueChange = {
                    updatedContent = it
                    contentError = when {
                        it.isEmpty() -> "El contenido no puede estar vacío"
                        it.length < 10 -> "El contenido debe tener al menos 10 caracteres"
                        else -> ""
                    }
                },
                label = "Contenido",
                modifier = Modifier.fillMaxWidth(),
                errors = listOf(TextFieldError(contentError.isNotEmpty(), contentError)),
                imeAction = ImeAction.Done,
                minLines = 20
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                DefaultButton(
                    onClick = {
                        if (updatedTitle.isEmpty()) titleError = "El título no puede estar vacío"
                        if (updatedContent.isEmpty()) contentError = "El contenido no puede estar vacío"

                        if (titleError.isEmpty() && contentError.isEmpty()) {
                            coroutineScope.launch {
                                try {
                                    val titlePart = updatedTitle.toRequestBody("text/plain".toMediaTypeOrNull())
                                    val contentPart = updatedContent.toRequestBody("text/plain".toMediaTypeOrNull())
                                    val diaryPart = diary.id.toRequestBody("text/plain".toMediaTypeOrNull())
                                    val imagePart = cameraViewModel.getSelectedBitmap()?.toMultipartBodyPart("image")

                                    PageServiceImp.getInstance().addPage(titlePart, contentPart, imagePart, diaryPart)
                                } catch (e: Exception) {
                                    Log.e("PageCreateScreen", "Error creating page", e)
                                }
                                Log.d("PageCreateScreen", "Page created")
                            }

                            navigator.navigate(PageListScreenDestination(diary))
                        }
                    }, text = "Guardar"
                )
            }
            }

    }
}








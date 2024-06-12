package com.fyp.app.ui.screens

import android.graphics.Bitmap
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.BuildConfig
import com.fyp.app.ui.components.TextFieldError
import com.fyp.app.ui.components.ValidatedTextField
import com.fyp.app.ui.components.buttons.DefaultButton
import com.fyp.app.utils.ValidatedTextFieldState

@Composable
fun FormScreenContent(
    title: String,
    firstImage: String = "",
    updatedImages: List<Bitmap>,
    onAddImageClick: () -> Unit,
    fields: List<ValidatedTextFieldState>,
    errors: List<String>,
    onSaveClick: () -> Unit
) {
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
                text = title,
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
                    .clickable { onAddImageClick() }
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                if (firstImage.isNotEmpty() && updatedImages.isEmpty()) {
                    AsyncImage(
                        model = BuildConfig.BACKEND_URL + firstImage,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else if (updatedImages.isNotEmpty()) {
                    Image(
                        bitmap = updatedImages.last().asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = "Añadir imagen",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                    )
                }
            }
        }

        fields.forEachIndexed { index, field ->
            item {
                Spacer(modifier = Modifier.height(16.dp))

                ValidatedTextField(
                    value = field.value,
                    onValueChange = { newValue -> field.onValueChange(newValue) },
                    label = field.label,
                    modifier = Modifier.fillMaxWidth(),
                    errors = listOf(TextFieldError(errors[index].isNotEmpty(), errors[index])),
                    imeAction = field.imeAction,
                    minLines = field.minLines
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                DefaultButton(
                    onClick = onSaveClick,
                    text = "Guardar"
                )
            }
        }
    }
}


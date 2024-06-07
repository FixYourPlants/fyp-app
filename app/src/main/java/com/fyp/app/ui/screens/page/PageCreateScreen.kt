package com.fyp.app.ui.screens.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.data.model.db.Diary
import com.fyp.app.ui.components.TextFieldError
import com.fyp.app.ui.components.ValidatedTextField
import com.fyp.app.ui.screens.destinations.PageCameraScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
@Destination
fun PageCreateScreen(
    navigator: DestinationsNavigator,
    diary: Diary,
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var titleError by remember { mutableStateOf(false) }
    var contentError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(2.dp)
            .background(Color(0xFF000500))
            .padding(2.dp)
            .background(Color(0xFF4CAF50))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val today = Calendar.getInstance().time
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormatter.format(today)

        Text(
            text = "Crear página en ${diary.title.lowercase()} el $formattedDate",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        ValidatedTextField(
            value = title,
            onValueChange = {
                title = it
                titleError = it.isEmpty()
            },
            label = "Título",
            errors = listOf(TextFieldError(titleError, "El título no puede estar vacío")),
            imeAction = ImeAction.Next,
            modifier = Modifier.fillMaxWidth()
        )

        ValidatedTextField(
            value = content,
            onValueChange = {
                content = it
                contentError = it.isEmpty()
            },
            label = "Contenido",
            errors = listOf(TextFieldError(contentError, "El contenido no puede estar vacío")),
            imeAction = ImeAction.Done,
            modifier = Modifier.fillMaxWidth(),
            minLines = 25
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    navigator.navigate(
                        PageCameraScreenDestination(title, content, diary)
                    )
                } else {
                    if (title.isEmpty()) titleError = true
                    if (content.isEmpty()) contentError = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Siguiente",
                color = Color(0xFF4CAF50)
            )
        }
    }
}








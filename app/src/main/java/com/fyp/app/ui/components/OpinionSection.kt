package com.fyp.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.data.model.db.Opinion
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun OpinionsSection(navigator: DestinationsNavigator, opinions: List<Opinion>) {
    Column {
        Box() {
            Column {
                opinions.forEach { opinion ->
                    OpinionCard(opinion = opinion, onClickUserImage = {
                        navigator.navigate(UserDetailsScreenDestination(opinion.user))
                    })
                }
            }
        }
    }
}

@Composable
fun OpinionCard(opinion: Opinion, onClickUserImage: () -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xA6349E06))
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .width(64.dp),
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    model = opinion.user.imageUrl,
                    contentDescription = "Image description",
                    modifier = Modifier
                        .size(43.dp)
                        .clip(CircleShape)
                        .clickable { onClickUserImage() }
                        .border(2.dp, Color(0xFF89FC1A), CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = opinion.user.username,
                    style = TextStyle(fontSize = 14.sp, color = Color(0xFF89FC1A), fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = opinion.title,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = Color(0xFF69FF18),
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = opinion.description,
                    style = TextStyle(fontSize = 14.sp),
                    color = Color.Black,
                    maxLines = if (expanded) Int.MAX_VALUE else 2,
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = "Expand",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = !expanded }
                )
            }
        }
    }
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
        color = Color.Black,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFFA5FFA9), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun AddOpinionDialog(onDismiss: () -> Unit, onSubmit: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val titleErrors = listOf(
        TextFieldError(isError = title.isEmpty(), errorMessage = "El título no puede estar vacío"),
        TextFieldError(isError = title.length < 3, errorMessage = "El título debe tener al menos 3 caracteres")
    )

    val descriptionErrors = listOf(
        TextFieldError(isError = description.isEmpty(), errorMessage = "La descripción no puede estar vacía"),
        TextFieldError(isError = description.length < 5, errorMessage = "La descripción debe tener al menos 5 caracteres")
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Añadir Opinión") },
        text = {
            Column {
                Text("Título")
                Spacer(modifier = Modifier.height(4.dp))
                ValidatedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = "Título",
                    errors = titleErrors,
                    imeAction = ImeAction.Next,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Descripción")
                Spacer(modifier = Modifier.height(4.dp))
                ValidatedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = "Descripción",
                    errors = descriptionErrors,
                    imeAction = ImeAction.Done,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (titleErrors.none { it.isError } && descriptionErrors.none { it.isError }) {
                        onSubmit(title, description)
                        onDismiss() // Opcionalmente cerrar el diálogo después de enviar
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF69FF18))
            ) {
                Text("Añadir", color = Color.Black)
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF69FF18))
            ) {
                Text("Cancelar", color = Color.Black)
            }
        },
        containerColor = Color(0xFF91CF50)
    )
}




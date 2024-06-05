package com.fyp.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.data.model.db.Opinion

@Composable
fun OpinionsSection(opinions: List<Opinion>) {
    Column {
        SectionTitle(
            title = "Opiniones",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.height(128.dp).verticalScroll(rememberScrollState())) {
            Column {
                opinions.forEach { opinion ->
                    OpinionCard(opinion = opinion)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}



@Composable
fun OpinionCard(opinion: Opinion) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = opinion.title,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = opinion.description,
                style = TextStyle(fontSize = 14.sp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Usuario: ${opinion.user.username}",
                style = TextStyle(fontSize = 14.sp, color = Color.Gray)
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Column {
        Text(
            text = title,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun AddOpinionDialog(onDismiss: () -> Unit, onSubmit: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Añadir Opinión") },
        text = {
            Column {
                Text("Título")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = title,
                    onValueChange = { title = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Descripción")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSubmit(title, description)
                    onDismiss()  // Optionally close the dialog after submitting
                }
            ) {
                Text("Añadir")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}


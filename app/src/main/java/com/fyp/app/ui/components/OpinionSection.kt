package com.fyp.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.ui.screens.Opinion


@Composable
fun OpinionsSection(opinions: List<Opinion>) {
    Column {
        SectionTitle(
            title = "Opiniones",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            opinions.forEach { opinion ->
                OpinionCard(opinion = opinion)
                Spacer(modifier = Modifier.height(8.dp))
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
                text = "Usuario: ${opinion.userName}",
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
fun AddOpinionDialog(onDismiss: () -> Unit) {
    var selectedUser by remember { mutableStateOf("Usuario1") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Añadir Opinión") },
        text = {
            Column {
                Text("Título")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(value = "", onValueChange = { /*TODO*/ })
                Spacer(modifier = Modifier.height(8.dp))
                Text("Descripción")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(value = "", onValueChange = { /*TODO*/ })
                Spacer(modifier = Modifier.height(8.dp))
                Text("Usuario")
                Spacer(modifier = Modifier.height(4.dp))
                DropdownMenu(
                    expanded = false,
                    onDismissRequest = { },
                    modifier = Modifier.width(IntrinsicSize.Min)
                ) {
                    DropdownMenuItem(
                        onClick = { selectedUser = "Usuario1" },
                        text = { Text("Usuario1") })
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    // Aquí deberías guardar la opinión y cerrar el diálogo
                    onDismiss()
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

val opinions = listOf(
    Opinion(
        title = "Opinión 1",
        description = "Esta es la primera opinión sobre la planta.",
        userName = "Usuario1"
    ),
    Opinion(
        title = "Opinión 2",
        description = "Esta es la segunda opinión sobre la planta.",
        userName = "Usuario2"
    ),
    Opinion(
        title = "Opinión 3",
        description = "Esta es la tercera opinión sobre la planta.",
        userName = "Usuario3"
    )
)

@Preview(showBackground = true)
@Composable
fun OpinionsSectionPreview() {
    OpinionsSection(opinions = opinions)
}

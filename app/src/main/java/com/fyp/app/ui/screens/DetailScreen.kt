package com.example.myapplication.domain.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.R
import com.fyp.app.ui.components.BoxTag
import com.fyp.app.ui.components.image.OverlayImageWithClick

data class Plant(
    val name: String,
    val description: String,
    val imageUrl: Int,
    val characteristics: List<String>,
    val dificulty: String = "Fácil",
    val treatments: List<String>,
    val sickness: List<String>,
    val opinions: List<String>,
    val scienceName: String
)

val plantDetails = Plant(
    name = "Lirio de la paz",
    description = "El lirio de la paz es una planta de interior popular por sus hojas verdes y brillantes.",
    imageUrl = R.drawable.down_down,
    characteristics = listOf(
        "Planta de interior",
        "Fácil de cuidar",
        "No necesita luz directa"
    ),
    dificulty = "Fácil",
    treatments = listOf(
        "Riego moderado",
        "Evitar corrientes de aire",
        "Fertilizar cada 2 semanas"
    ),
    sickness = listOf(
        "Hojas amarillas",
        "Pudrición de raíces"
    ),
    opinions = listOf(
        "Me encanta esta planta",
        "Fácil de cuidar",
        "Muy bonita"
    ),
    scienceName = "Spathiphyllum wallisii"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantDetailsScreen(
    plant: Plant,
    onBackClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(0.5f)
                ) {
                    OverlayImageWithClick(
                        defaultImageUrl = plant.imageUrl,
                        clickedImageUrl = R.drawable.down_down,
                        onClick = { /* Acciones al hacer clic en la imagen */ }
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = plant.name,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                    Text(
                        text = plant.scienceName,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = plant.description,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.Black
                    )
                    Text(
                        text = "Dificultad: ",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = plant.dificulty,
                        color = Color.Black
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            BoxTag(name = "Características", values = plant.characteristics)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cuidado recomendado",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            plant.treatments.forEach { treatment ->
                Text(
                    text = "• $treatment",
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = Color.Black
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            BoxTag(name = "Enfermedades:", values = plant.sickness)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Opiniones",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            plant.opinions.forEach { opinion ->
                Text(
                    text = "• $opinion",
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = Color.Black
                )
            }
        }
    }
}




@Composable
@Preview
fun PlantDetailsScreenPreview() {
    PlantDetailsScreen(plant = plantDetails, onBackClicked = {})
}







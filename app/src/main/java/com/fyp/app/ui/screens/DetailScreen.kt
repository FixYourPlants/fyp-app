package com.example.myapplication.domain.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fyp.app.R
import com.fyp.app.ui.components.image.OverlayImageWithClick

data class Plant(val name: String, val description: String, val imageUrl: Int)

val plantDetails = Plant(
    name = "Lirio de la paz",
    description = "El lirio de la paz es una planta de interior popular por sus hojas verdes y brillantes.",
    imageUrl = R.drawable.down_down
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantDetailsScreen(
    plant: Plant,
    onBackClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = plant.name
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBackClicked() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            item {
                Column {
                    // Utiliza el composable OverlayImageWithClick
                    OverlayImageWithClick(
                        defaultImageUrl = plant.imageUrl,
                        clickedImageUrl = R.drawable.down_down,
                        onClick = { /* Acciones al hacer clic en la imagen */ }
                    )
                }

            }
            item {
                Column {
                    Text(
                        text = plant.description,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PlantDetailsScreenPreview() {
    PlantDetailsScreen(plant = plantDetails, onBackClicked = {})
}







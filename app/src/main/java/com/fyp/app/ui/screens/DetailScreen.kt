package com.example.myapplication.domain.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fyp.app.R

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

@Composable
fun OverlayImageWithClick(
    defaultImageUrl: Int,
    clickedImageUrl: Int,
    onClick: () -> Unit
) {
    var isImageClicked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        // Fondo: Imagen de la planta
        Image(
            painter = painterResource(id = defaultImageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        // Frente: Imagen superpuesta con cambio de imagen al hacer clic
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.medium)
                .clickable {
                    isImageClicked = !isImageClicked
                    onClick()
                },
        ) {
            // Ajusta el tamaño y la posición de la imagen superpuesta
            val imageSize = if (isImageClicked) 100.dp else 50.dp
            Image(
                painter = painterResource(id = if (isImageClicked) clickedImageUrl else defaultImageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(imageSize)
                    .offset(16.dp, 16.dp)  // Ajusta la posición según tus necesidades
                    .clip(CircleShape),  // Puedes ajustar la forma según tus necesidades
                contentScale = ContentScale.Crop
            )
        }
    }
}





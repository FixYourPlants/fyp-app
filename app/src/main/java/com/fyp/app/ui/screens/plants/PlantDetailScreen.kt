package com.fyp.app.ui.screens.plants

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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.R
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.obtainDifficulty
import com.fyp.app.ui.components.AddOpinionDialog
import com.fyp.app.ui.components.BoxTag
import com.fyp.app.ui.components.OpinionsSection
import com.fyp.app.ui.components.image.OverlayImageWithClick
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantDetailsScreen(
    navigator: DestinationsNavigator,
    plant: Plant
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    OverlayImageWithClick(
                        defaultImageUrl = R.drawable.plants,
                        clickedImageUrl = R.drawable.hearth,
                        notClickedImageUrl = R.drawable.hearth_empty,
                        onClick = { /* Acciones cuando se hace clic en la imagen */ }
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Text(
                        text = plant.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                    Text(
                        text = plant.scientificName,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.Black
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
                        text = obtainDifficulty(plant.difficulty),
                        color = Color.Black
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            BoxTag(name = "Características", values = plant.characteristics.map { it -> it.toString() })
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cuidado recomendado",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(2.dp),
                color = Color(0xFFA5FFA9)
            ) {
                Column {
                        Text(
                            text = plant.treatment,
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = Color.Black
                        )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            BoxTag(name = "Enfermedades:", values = plant.sicknesses.map { it -> it.toString() })
        }

        item {
            var showDialog by remember { mutableStateOf(false) }

            Column(modifier = Modifier.padding(16.dp)) {
                OpinionsSection(opinions = mutableListOf())
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showDialog = true }) {
                    Text(text = "Añadir Opinión")
                }
            }

            if (showDialog) {
                AddOpinionDialog(onDismiss = { showDialog = false })
            }
        }
    }
}







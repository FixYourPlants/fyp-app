package com.fyp.app.ui.screens.plants

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.R
import com.fyp.app.data.api.PlantServiceImp
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.obtainDifficulty
import com.fyp.app.ui.components.*
import com.fyp.app.ui.components.image.OverlayImageWithClick
import com.fyp.app.ui.screens.destinations.IllnessDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Destination
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
        item { PlantDetailsHeader(plant) }
        item { PlantCharacteristicsSection(plant) }
        item { PlantCareSection(plant) }
        item { PlantSicknessesSection(plant, navigator) }
        item { PlantOpinionsSection() }
    }
}

@Composable
fun PlantDetailsHeader(plant: Plant) {
    val status = remember { mutableStateOf(false) }

    // LaunchedEffect to check the favorite status of the plant
    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                PlantServiceImp.getInstance().statusFavPlant(plant.id)
            } catch (e: Exception) {
                false
            }
        }
        status.value = result
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.5f)) {
            OverlayImageWithClick(
                defaultImageUrl = plant.imageUrl,
                clickedImageUrl = if (status.value) R.drawable.hearth else R.drawable.hearth_empty,
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val result = try {
                            val value = PlantServiceImp.getInstance().toggleFavPlant(plant.id)
                            Log.d("PlantDetailsScreen", "Favorite status: $value")
                            value
                        } catch (e: Exception) {
                            false
                        }
                        withContext(Dispatchers.Main) {
                            status.value = result
                        }
                    }
                }
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(0.5f)) {
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

@Composable
fun PlantCharacteristicsSection(plant: Plant) {
    Spacer(modifier = Modifier.height(16.dp))
    BoxTag(
        name = "Características",
        values = plant.characteristics.map { it.name }
    )
}

@Composable
fun PlantCareSection(plant: Plant) {
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlantSicknessesSection(plant: Plant, navigator: DestinationsNavigator) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF2E5805), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = "Enfermedades:",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            plant.sicknesses.forEach { sickness ->
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clickable { navigator.navigate(IllnessDetailsScreenDestination(sickness)) }
                        .background(Color(0xFF7CFC00), RoundedCornerShape(8.dp))
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = sickness.name,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PlantOpinionsSection() {
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









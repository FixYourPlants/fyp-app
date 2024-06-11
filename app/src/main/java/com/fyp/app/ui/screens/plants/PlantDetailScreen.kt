package com.fyp.app.ui.screens.plants

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.R
import com.fyp.app.data.api.OpinionServiceImp
import com.fyp.app.data.api.PlantServiceImp
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.data.model.db.CreateOpinion
import com.fyp.app.data.model.db.Opinion
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.User
import com.fyp.app.data.model.db.obtainDifficulty
import com.fyp.app.ui.components.AddOpinionDialog
import com.fyp.app.ui.components.BoxTag
import com.fyp.app.ui.components.OpinionsSection
import com.fyp.app.ui.components.OverlayImageWithClick
import com.fyp.app.ui.screens.destinations.IllnessDetailsScreenDestination
import com.fyp.app.utils.UserPreferencesImp
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
            .background(Color(0xFF91CF50))
            .padding(16.dp)
    ) {
        item { PlantDetailsHeader(plant) }
        item { PlantCharacteristicsSection(plant) }
        item { PlantCareSection(plant) }
        item { PlantSicknessesSection(plant, navigator) }
        item { PlantOpinionsSection(navigator, plant) }
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
            if (UserPreferencesImp.isAuthenticated()) {
                OverlayImageWithClick(
                    defaultImageUrl = plant.imageUrl,
                    clickedImageUrl = if (status.value) R.drawable.hearth else R.drawable.hearth_empty,
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            val result = try {
                                PlantServiceImp.getInstance().toggleFavPlant(plant.id)
                            } catch (e: Exception) {
                                false
                            }
                            withContext(Dispatchers.Main) {
                                status.value = result
                            }
                        }
                    }
                )
            } else {
                AsyncImage(
                    model = plant.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = MaterialTheme.shapes.medium)
                        .border(
                            width = 2.0.dp,
                            color = Color.Black,
                            shape = MaterialTheme.shapes.medium
                        ),
                    contentScale = ContentScale.Crop
                )
            }
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
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp),
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
fun PlantOpinionsSection(navigator: DestinationsNavigator, plant: Plant) {
    var showDialog by remember { mutableStateOf(false) }
    val user: MutableState<User?> = remember { mutableStateOf(null) }
    val opinions: MutableState<List<Opinion>> = remember { mutableStateOf(mutableListOf()) }

    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                UserServiceImp.getInstance().getLoggedInUser()
            } catch (e: Exception) {
                null
            }
        }
        user.value = result
    }

    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                OpinionServiceImp.getInstance().getOpinions(plant.id)
            } catch (e: Exception) {
                Log.e("PlantDetailsScreen", "Error getting opinions", e)
                null
            }
        }
        Log.d("PlantDetailsScreen", "Opinions: $result")
        if (result != null) {
            opinions.value = result
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {

        OpinionsSection(navigator, opinions.value)
        Spacer(modifier = Modifier.height(16.dp))
        if (UserPreferencesImp.isAuthenticated()) {
            Button(onClick = { showDialog = true }) {
                Text(text = "Añadir Opinión")
            }
        }
    }

    if (showDialog && user.value != null) {
        AddOpinionDialog(onDismiss = { showDialog = false }, onSubmit = { title, description ->
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val opinion = CreateOpinion(
                        title = title,
                        description = description,
                        user = user.value!!,
                        plantId = plant.id
                    )
                    OpinionServiceImp.getInstance().addOpinion(opinion)
                } catch (e: Exception) {
                    Log.e("PlantDetailsScreen", "Error adding opinion", e)
                }
            }


        })
    } else if (user.value == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Inicia sesión para añadir una opinión", color = Color.Red)
        }
    }
}









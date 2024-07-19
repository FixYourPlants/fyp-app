package com.fyp.app.ui.screens.plants

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.fyp.app.BuildConfig
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
import com.fyp.app.ui.components.BoxLongText
import com.fyp.app.ui.components.BoxTag
import com.fyp.app.ui.components.DetailBackground
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

@Composable
@Destination
fun PlantDetailsScreen(
    navigator: DestinationsNavigator,
    plant: Plant
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    DetailBackground {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .paint(
                    painterResource(id = R.drawable.plantas_top),
                    alignment = AbsoluteAlignment.TopRight
                )
                .border(
                    width = 3.0.dp,
                    color = Color(59, 170, 0, 255),
                    shape = RoundedCornerShape(20.dp),
                )
        ) {
            item {
                Spacer(modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth())
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .background(Color(226, 237, 169, 255), shape = RoundedCornerShape(20.dp))
                        .zIndex(0f)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        PlantDetailsHeader(plant)
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-70).dp)){
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)) {
                                TabRow(selectedTabIndex = pagerState.currentPage,
                                    modifier = Modifier
                                ) {
                                    listOf("Detalles", "Opiniones").forEachIndexed { index, title ->
                                        Tab(
                                            text = { Text(title, color = Color.Black) },
                                            selected = pagerState.currentPage == index,
                                            onClick = {
                                                coroutineScope.launch {
                                                    pagerState.animateScrollToPage(index)
                                                }
                                            },
                                            modifier = Modifier
                                                .background(Color(226, 237, 169, 255))
                                        )

                                    }
                                }
                                HorizontalPager( state = pagerState) { page ->
                                    when (page) {
                                        0 -> PlantDetailsTab(navigator,plant)
                                        1 -> PlantOpinionsTab(navigator,plant)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlantDetailsTab(navigator: DestinationsNavigator,plant: Plant){
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()){
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)){
                PlantCharacteristicsSection(plant)
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()){
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Cuidado recomendado",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = Color(59, 170, 0, 255),
                    modifier = Modifier.padding(4.dp)
                )
                PlantCareSection(plant)
                Spacer(modifier = Modifier.height(12.dp))
                PlantSicknessesSection(plant,navigator)
            }
        }
    }
}

@Composable
fun PlantOpinionsTab(navigator: DestinationsNavigator,plant: Plant) {
    PlantOpinionsSection(navigator,plant)
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-80).dp),
        contentAlignment = Alignment.TopCenter
    ) {
        if (UserPreferencesImp.isAuthenticated()) {
            Log.d("PlantDetailsScreen", "Status: ${plant.imageUrl}")
            OverlayImageWithClick(
                defaultImageUrl = if(plant.imageUrl.contains("https") || plant.imageUrl.contains("http")) plant.imageUrl else BuildConfig.BACKEND_URL + plant.imageUrl,
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
                    .size(150.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .border(
                        width = 2.0.dp,
                        color = Color.Black,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .zIndex(2f),
                contentScale = ContentScale.Crop
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-70).dp),
        contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = plant.name,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black,
            )
            Text(
                text = plant.scientificName,
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = plant.description,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black,
                textAlign = TextAlign.Justify
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
    BoxTag(
        name = "Características",
        values = plant.characteristics.map { it.name }
    )
}

@Composable
fun PlantCareSection(plant: Plant) {
    BoxLongText(text = plant.treatment)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlantSicknessesSection(plant: Plant, navigator: DestinationsNavigator) {
    Column {
        Text(
            text = "Enfermedades",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        HorizontalDivider(
            thickness = 2.dp,
            color = Color(59, 170, 0, 255),
            modifier = Modifier.padding(4.dp)
        )
        plant.sicknesses.forEach { it ->
            Text(text = it.name,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .clickable(onClick = {
                        navigator.navigate(IllnessDetailsScreenDestination(it))
                    }
                    )
            )
        }
    }
}

@Composable
fun PlantOpinionsSection(navigator: DestinationsNavigator, plant: Plant) {
    var showDialog by remember { mutableStateOf(false) }
    val user: MutableState<User?> = remember { mutableStateOf(null) }
    val opinions: MutableState<List<Opinion>> = remember { mutableStateOf(mutableListOf()) }
    var showLoginWarning by remember { mutableStateOf(false) }

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
        Button(
            onClick = {
                if (UserPreferencesImp.isAuthenticated()) {
                    showDialog = true
                    showLoginWarning = false
                } else{
                    showLoginWarning = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xA6349E06))
        ) {
            Text(text = "Añadir Opinión", color = Color.Black)
        }
        if (showLoginWarning) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Inicia sesión para añadir una opinión", color = Color.Red)
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        OpinionsSection(navigator,opinions.value)
    }

    if (showDialog && user.value != null) {
        AddOpinionDialog(
            onDismiss = { showDialog = false },
            onSubmit = { title, description ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val opinion = CreateOpinion(
                            title = title,
                            description = description,
                            user = user.value!!,
                            plantId = plant.id
                        )
                        OpinionServiceImp.getInstance().addOpinion(opinion)
                        withContext(Dispatchers.Main) {
                            showDialog = false
                            // Refresh opinions
                            opinions.value = OpinionServiceImp.getInstance().getOpinions(plant.id)
                        }
                    } catch (e: Exception) {
                        Log.e("PlantDetailsScreen", "Error adding opinion", e)
                    }
                }
            }
        )
    }
}
package com.fyp.app.ui.screens.users

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.fyp.app.BuildConfig
import com.fyp.app.R
import com.fyp.app.data.api.PlantServiceImp
import com.fyp.app.data.api.SicknessServiceImp
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.data.model.db.User
import com.fyp.app.ui.components.BoxLongText
import com.fyp.app.ui.components.DetailBackground
import com.fyp.app.ui.components.buttons.DefaultButton
import com.fyp.app.ui.screens.destinations.IllnessDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.PlantDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.UserEditScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
@Destination
fun UserDetailsScreen(
    navigator: DestinationsNavigator,
    user: User
) {
    val favouritePlants = remember { mutableStateOf(listOf<Plant>()) }
    val affectedSickness = remember { mutableStateListOf<Sickness>() }

    LaunchedEffect(Unit) {
        val plants = user.favouritePlants.mapNotNull { plantId ->
            withContext(Dispatchers.IO) {
                try {
                    PlantServiceImp.getInstance().getPlantById(plantId)
                } catch (e: Exception) {
                    Log.e("UserDetailsScreen", "Error loading plant", e)
                    null
                }
            }
        }
        favouritePlants.value = plants
    }

    LaunchedEffect(Unit) {
        val result = user.affectedSickness.mapNotNull { sicknessId ->
            withContext(Dispatchers.IO) {
                try {
                    SicknessServiceImp.getInstance().getSicknessById(sicknessId)
                } catch (e: Exception) {
                    Log.e("AffectedSicknessListScreen", "Error loading sickness", e)
                    null
                }
            }
        }
        affectedSickness.clear()
        affectedSickness.addAll(result)
    }

    DetailBackground {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 3.0.dp,
                    color = Color(59, 170, 0, 255),
                    shape = RoundedCornerShape(8.dp),
                )
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .background(Color(226, 237, 169, 255), shape = RoundedCornerShape(8.dp))
                        .zIndex(0f)
                ){
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray)
                                .paint(painterResource(id = R.drawable.corte_hierba), contentScale = ContentScale.FillBounds)
                                .padding(16.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                AsyncImage(
                                    model = if(user.imageUrl.contains("https") || user.imageUrl.contains("http")) user.imageUrl else BuildConfig.BACKEND_URL + user.imageUrl,
                                    placeholder = painterResource(id = R.drawable.default_user),
                                    error = painterResource(id = R.drawable.default_user),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clip(shape = RoundedCornerShape(8.dp))
                                        .border(
                                            width = 2.0.dp,
                                            color = Color(139, 195, 74, 255),
                                            shape = RoundedCornerShape(8.dp),
                                        ),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = user.username,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp,
                                        color = Color.White
                                    )
                                    if (user.firstName != null && user.lastName != null) {
                                        Text(
                                            text = "${user.firstName} ${user.lastName}",
                                            fontStyle = FontStyle.Italic,
                                            fontSize = 18.sp,
                                            color = Color.White
                                        )
                                    }
                                    if (user.email != null) {
                                        Text(
                                            text = user.email,
                                            fontStyle = FontStyle.Italic,
                                            fontSize = 12.sp,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                        // Transformar esto en LazyColumn
                        Column(modifier = Modifier.padding(8.dp)) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Sobre mí...",
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
                            BoxLongText(text = user.aboutMe)
                        }
                        Log.d("UserDetailsScreen", "Favourite plants: ${favouritePlants.value}")
                        UserFavoritePlantsSection(plants = favouritePlants.value, navigator)
                        UserAffectedSicknessSection(sickness = affectedSickness, navigator)

                        Box(
                            modifier= Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center){
                            DefaultButton(
                                onClick = { navigator.navigate(UserEditScreenDestination(user)) },
                                text = "Editar Usuario"
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserFavoritePlantsSection(plants: List<Plant>, navigator: DestinationsNavigator) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF2E5805), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = "Mis plantas:",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            plants.forEach { plant ->
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clickable { navigator.navigate(PlantDetailsScreenDestination(plant)) }
                        .background(Color(0xFF7CFC00), RoundedCornerShape(8.dp))
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = plant.name,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserAffectedSicknessSection(sickness: List<Sickness>, navigator: DestinationsNavigator) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF2E5805), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = "Mis Enfermedades:",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            sickness.forEach { illness ->
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clickable { navigator.navigate(IllnessDetailsScreenDestination(illness)) }
                        .background(Color(0xFF7CFC00), RoundedCornerShape(8.dp))
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = illness.name,
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




package com.fyp.app.ui.screens.users

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.BuildConfig
import com.fyp.app.R
import com.fyp.app.data.api.PlantServiceImp
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.User
import com.fyp.app.ui.components.BoxLongText
import com.fyp.app.ui.components.buttons.DefaultButton
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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(0.5f)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        AsyncImage(
                            model = BuildConfig.BACKEND_URL + user.imageUrl,
                            placeholder = painterResource(id = R.drawable.default_user),
                            error = painterResource(id = R.drawable.default_user),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(0.5f)) {
                    Text(
                        text = user.username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                    if(user.firstName != null){
                        Text(
                            text = "${user.firstName} ${user.lastName}",
                            fontStyle = FontStyle.Italic,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = Color.Black
                        )
                    }
                    if (user.lastName != null){
                        Text(
                            text = user.email,
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.Black
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Sobre mí...",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            BoxLongText(text = user.aboutMe)
        }
        item {
            val favouritePlants = remember { mutableStateOf(listOf<Plant>()) }

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
            Spacer(modifier = Modifier.height(16.dp))
            Log.d("UserDetailsScreen", "Favourite plants: ${favouritePlants.value}")
            UserFavoritePlantsSection(plants = favouritePlants.value, navigator = navigator)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            DefaultButton(
                onClick = { navigator.navigate(UserEditScreenDestination(user)) },
                text = "Edit User"
            )
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




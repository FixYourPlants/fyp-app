package com.fyp.app.ui.screens.plants

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fyp.app.data.api.PlantServiceImp
import com.fyp.app.data.model.db.Plant
import com.fyp.app.ui.components.ContainerPlants
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.PlantDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.fyp.app.utils.filterPlants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
@Destination
fun PlantListScreen(navigator: DestinationsNavigator) {
    val plants = remember { mutableStateListOf<Plant>() }
    val service = PlantServiceImp.getInstance()
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                service.getPlants()
            } catch (e: Exception) {
                emptyList<Plant>() // Devolvemos una lista vacía en caso de error
            }
        }
        plants.clear()
        plants.addAll(result)
    }

    Column {
        Header(
            onClickLogo = {
                navigator.navigate(HomeScreenDestination())
            },
            onClickAccount = {
                navigator.navigate(UserDetailsScreenDestination())
            }
        )

        TextField(
            value = searchText,
            onValueChange = { newText -> searchText = newText },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search") }
        )

        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .border(width = 2.dp, color = Color.Black)
        ) {
            val filteredPlants = if (searchText.isNotEmpty()) {
                filterPlants(searchText, plants)
            } else {
                plants
            }

            items(filteredPlants) { plant ->
                ContainerPlants(plant, onClick = {
                    navigator.navigate(PlantDetailsScreenDestination(plant))
                })
            }
        }
    }
}

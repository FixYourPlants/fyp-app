package com.fyp.app.ui.screens.plants

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fyp.app.data.api.PlantServiceImp
import com.fyp.app.data.model.db.Plant
import com.fyp.app.ui.components.ContainerPlants
import com.fyp.app.ui.components.HeaderSection
import com.fyp.app.ui.components.LazyColumnList
import com.fyp.app.ui.components.SearchField
import com.fyp.app.ui.screens.destinations.PlantDetailsScreenDestination
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
                emptyList<Plant>()
            }
        }
        plants.clear()
        plants.addAll(result)
    }

    Column {
        HeaderSection(navigator)
        SearchField(searchText, onSearchTextChanged = { searchText = it })
        LazyColumnList(
            items = if (searchText.isNotEmpty()) filterPlants(searchText, plants) else plants
        ) { plant ->
            ContainerPlants(plant, onClick = {
                navigator.navigate(PlantDetailsScreenDestination(plant))
            })
        }
    }
}

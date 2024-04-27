package com.fyp.app.ui.screens.plants

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.fyp.app.data.api.ApiModule
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.components.ListBoxPlants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.fyp.app.data.model.Plant
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination

@Composable
@Destination
fun PlantListScreen(navigator: DestinationsNavigator) {
    val plants = remember { mutableStateListOf<Plant>() }
    val service = ApiModule.apiService
    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                service.getPlants()
            } catch (e: Exception) {
                Log.e("Error-API", e.stackTraceToString())
                emptyList<Plant>() // Devolvemos una lista vacía en caso de error
            }
        }
        plants.clear()
        plants.addAll(result as List<Plant>)
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
        ListBoxPlants(content = plants)
    }
}
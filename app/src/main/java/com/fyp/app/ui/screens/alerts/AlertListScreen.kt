package com.fyp.app.ui.screens.alerts

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
import com.fyp.app.data.api.AlertServiceImp
import com.fyp.app.data.model.db.Alert
import com.fyp.app.ui.components.ContainerPlague
import com.fyp.app.ui.components.ContainerPlagueGob
import com.fyp.app.ui.components.ContainerPlants
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.components.HeaderSection
import com.fyp.app.ui.components.LazyColumnList
import com.fyp.app.ui.components.SearchField
import com.fyp.app.ui.screens.destinations.AlertDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.PlantDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.fyp.app.utils.filterPlants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

@Composable
@Destination
fun AlertsListScreen(navigator: DestinationsNavigator) {
    val alerts = remember { mutableStateListOf<Alert>() }
    val service = AlertServiceImp.getInstance()

    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                service.getAlertsGob()
            } catch (e: Exception) {
                emptyList() // Devolvemos una lista vacía en caso de error
            }
        }
        alerts.clear()
        alerts.addAll(result)
    }

    Column {
        HeaderSection(navigator)
        LazyColumnList(
            items = alerts
        ) { alert ->
            ContainerPlagueGob(alert,
                 onClick = {
                     navigator.navigate(AlertDetailsScreenDestination(alert))
            })
        }
    }
}
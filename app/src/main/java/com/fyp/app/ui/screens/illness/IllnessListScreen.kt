package com.fyp.app.ui.screens.illness

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.fyp.app.data.api.ApiModule
import com.fyp.app.data.model.Illness
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.components.ListBoxIllness
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
@Destination
fun IllnessListScreen(navigator: DestinationsNavigator) {
    val illness = remember { mutableListOf<Illness>() }
    val service = ApiModule.apiService

    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                service.getIllnesses()
            } catch (e: Exception) {
                emptyList() // Devolvemos una lista vacía en caso de error
            }
        }
        illness.clear()
        illness.addAll(result)
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
        ListBoxIllness(content = illness)
    }
}
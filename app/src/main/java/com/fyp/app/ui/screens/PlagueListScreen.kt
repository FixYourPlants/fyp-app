package com.fyp.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.components.ListBoxAlerts
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun PlaguesListScreen(navigator: DestinationsNavigator) {
    val plagues = remember { getPlaguesList() }
    Column {
        Header(
            onClickLogo = {
                navigator.navigate(HomeScreenDestination())
            },
            onClickAccount = {
                navigator.navigate(UserDetailsScreenDestination())
            }
        )
        ListBoxAlerts(content = plagues)
    }
}

fun getPlaguesList(): MutableList<MutableList<String>> {
    val content = mutableListOf(mutableListOf("Alerta1","image1","Descripción 1"),mutableListOf("Alerta2","image2","Descripción 2"),mutableListOf("Alerta2","image2","Descripción 2"))
    return content
}


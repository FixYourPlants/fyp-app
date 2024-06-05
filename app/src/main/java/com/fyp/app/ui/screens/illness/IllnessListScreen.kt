package com.fyp.app.ui.screens.illness

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fyp.app.data.api.SicknessServiceImp
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.ui.components.ContainerIllness
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.fyp.app.utils.filterSickness
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
@Destination
fun IllnessListScreen(navigator: DestinationsNavigator) {
    val sicknesses = remember { mutableStateListOf<Sickness>() }
    val service = SicknessServiceImp.getInstance()
    var searchText by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                service.getSicknesses()
            } catch (e: Exception) {
                emptyList() // Devolvemos una lista vacía en caso de error
            }
        }
        sicknesses.clear()
        sicknesses.addAll(result)
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
        LazyColumn(modifier = Modifier
            .padding(10.dp)
            .border(width = 2.dp, color = Color.Black)
        ) {
            val filteredSicknesses = if (searchText.isNotEmpty()) {
                filterSickness(searchText, sicknesses)
            } else {
                sicknesses
            }
            items(filteredSicknesses) { sickness ->
                ContainerIllness(sickness, onClick = {
                    navigator.navigate(com.fyp.app.ui.screens.destinations.IllnessDetailsScreenDestination(sickness))
                })
            }
        }
    }
}
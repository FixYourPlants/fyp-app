package com.fyp.app.ui.screens.illness

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fyp.app.data.api.SicknessServiceImp
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.ui.components.ContainerIllness
import com.fyp.app.ui.components.HeaderSection
import com.fyp.app.ui.components.LazyColumnList
import com.fyp.app.ui.components.SearchField
import com.fyp.app.ui.screens.destinations.IllnessDetailsScreenDestination
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
                emptyList()
            }
        }
        sicknesses.clear()
        sicknesses.addAll(result)
    }

    Column {
        HeaderSection(navigator)
        SearchField(searchText, onSearchTextChanged = { searchText = it })
        LazyColumnList(
            items = if (searchText.isNotEmpty()) filterSickness(searchText, sicknesses) else sicknesses
        ) { sickness ->
            ContainerIllness(sickness, onClick = {
                navigator.navigate(IllnessDetailsScreenDestination(sickness))
            })
        }
    }
}

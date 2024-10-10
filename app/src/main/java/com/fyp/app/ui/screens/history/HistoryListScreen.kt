package com.fyp.app.ui.screens.history

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fyp.app.data.api.HistoryServiceImp
import com.fyp.app.data.model.db.History
import com.fyp.app.ui.components.ContainerHistory
import com.fyp.app.ui.components.HeaderSection
import com.fyp.app.ui.components.LazyColumnList
import com.fyp.app.ui.components.SearchField
import com.fyp.app.ui.screens.destinations.HistoryDetailsScreenDestination
import com.fyp.app.utils.filterHistory
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
@Destination
fun HistoryListScreen(navigator: DestinationsNavigator) {
    val histories = remember { mutableStateListOf<History>() }
    val service = HistoryServiceImp.getInstance()
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                service.getHistories()
            } catch (e: Exception) {
                emptyList()
            }
        }
        histories.clear()
        histories.addAll(result)
    }

    Column {
        HeaderSection(navigator)
        SearchField(searchText, onSearchTextChanged = { searchText = it })
        LazyColumnList(
            items = if (searchText.isNotEmpty()) filterHistory(searchText, histories) else histories
        ) { history ->
            ContainerHistory(history, onClick = {
                navigator.navigate(HistoryDetailsScreenDestination(history))
            })
        }
    }
}

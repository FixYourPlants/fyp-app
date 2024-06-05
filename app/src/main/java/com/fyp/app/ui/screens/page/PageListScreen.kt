package com.fyp.app.ui.screens.page

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fyp.app.data.api.PageServiceImp
import com.fyp.app.data.model.db.Diary
import com.fyp.app.data.model.db.Page
import com.fyp.app.ui.components.HeaderSection
import com.fyp.app.ui.components.LazyColumnList
import com.fyp.app.ui.components.PageItem
import com.fyp.app.ui.screens.destinations.PageCreateScreenDestination
import com.fyp.app.ui.screens.destinations.PageDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
@Destination
fun PageListScreen(navigator: DestinationsNavigator, diary: Diary) {
    val pages = remember { mutableStateListOf<Page>() }
    val showCreationMessage by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                PageServiceImp.getInstance().getPages(diary.id)
            } catch (e: Exception) {
                Log.e("PageListScreen", "Error getting pages", e)
                emptyList()
            }
        }
        pages.clear()
        pages.addAll(result)
    }

    Column {
        HeaderSection(navigator)
        Button(
            onClick = {
                navigator.navigate(PageCreateScreenDestination(diary = diary))
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("Crear Página")
        }
        LazyColumnList(items = pages) { page ->
            PageItem(page, onClick = { navigator.navigate(PageDetailsScreenDestination(page)) })
        }
        if (showCreationMessage) {
            Text(
                text = "La página para hoy ya ha sido creada.",
                color = Color.Red,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}


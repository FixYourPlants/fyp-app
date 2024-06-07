package com.fyp.app.ui.screens.page

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
@Destination
fun PageListScreen(navigator: DestinationsNavigator, diary: Diary) {
    val pages = remember { mutableStateListOf<Page>() }
    val showCreationMessage = remember { mutableStateOf(false) }
    val dates = remember { mutableStateListOf<Date>() }
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Cambio de formato
    val today = inputFormat.parse(inputFormat.format(System.currentTimeMillis()))

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
        dates.addAll(pages.map { inputFormat.parse(it.createdAt.split("T")[0]) }) // Solo tomamos la parte de la fecha y omitimos la parte de la hora
        Log.d("PageListScreen", "Today: $today")
        Log.d("PageListScreen", "Dates: $dates")
        Log.d("PageListScreen", "Today in dates: ${dates.contains(today)}")
    }

    Column {
        HeaderSection(navigator)

        Button(
            onClick = {
                if (today?.let { dates.contains(it) } == true) {
                    showCreationMessage.value = true
                }
                if (!showCreationMessage.value) {
                    navigator.navigate(PageCreateScreenDestination(diary = diary))
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("Crear Página")
        }
        LazyColumnList(items = pages) { page ->
            PageItem(page, onClick = {
                Log.d("PageListScreen", "Navigating to page details ${!showCreationMessage.value}")
                navigator.navigate(PageDetailsScreenDestination(page))
            })
        }
        if (showCreationMessage.value) {
            Toast.makeText(
                LocalContext.current,
                "La página para hoy ya ha sido creada.",
                Toast.LENGTH_SHORT
            ).show()
            showCreationMessage.value = false
        }
    }
}


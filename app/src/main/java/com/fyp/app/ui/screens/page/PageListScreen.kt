package com.fyp.app.ui.screens.page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.data.api.PageServiceImp
import com.fyp.app.data.model.db.Diary
import com.fyp.app.data.model.db.Page
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.PageCreateScreenDestination
import com.fyp.app.ui.screens.destinations.PageDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@Composable
@Destination
fun PageListScreen(navigator: DestinationsNavigator, diary: Diary) {
    val pages = remember { mutableStateListOf<Page>() }
    var showCreationMessage by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                PageServiceImp.getInstance().getPages(diary.id)
            } catch (e: Exception) {
                Log.e("PageListScreen", "Error getting pages", e)
                emptyList() // Devolvemos una lista vacía en caso de error
            }
        }
        pages.clear()
        pages.addAll(result)
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
        Button(
            onClick = {
                navigator.navigate(PageCreateScreenDestination(diary = diary))
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("Crear Página")
        }
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .border(width = 2.dp, color = Color.Black)
        ) {
            items(pages) { page ->
                PageItem(page, onClick = { navigator.navigate(PageDetailsScreenDestination(page)) })
            }
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

@Composable
fun PageItem(page: Page, onClick: (Page) -> Unit) {
    Box(contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .clickable { onClick(page) }
            .background(color = Color(146, 208, 80))
            .border(width = 0.5.dp, color = Color.Black)
    ) {
        Column(Modifier.padding(horizontal = 5.dp)) {
            Surface(
                modifier = Modifier
                    .requiredSize(84.dp)
                    .border(width = 1.dp, color = Color.Black)
            ) {
                AsyncImage(
                    model = page.imageUrl,
                    contentDescription = "Image description",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(color = Color.White)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 94.dp)) {
            Text(
                text = page.title,
                fontSize = 18.sp,
                color = Color(0, 176, 80),
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Fecha: ${page.createdAt}",
                color = Color.White,
                maxLines = 1
            )
        }
    }
}

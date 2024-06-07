package com.fyp.app.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.data.api.DiaryServiceImp
import com.fyp.app.data.model.db.Diary
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.PageListScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
@Destination
fun DiariesScreen(navigator: DestinationsNavigator) {
    val diaries = remember { mutableStateListOf<Diary>() }
    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                DiaryServiceImp.getInstance().getDiaries()
            } catch (e: Exception) {
                Log.e("DiariesScreen", "Error getting diaries", e)
                emptyList() // Devolvemos una lista vacía en caso de error
            }
        }
        diaries.clear()
        diaries.addAll(result)
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
        LazyColumn(modifier = Modifier
            .padding(10.dp)
            .border(width = 2.dp, color = Color.Black)
        ) {
            items(diaries){ diary ->
                Log.d("DiariesScreen", "Diary: $diary")
                DiaryItem(diary, onClick = { navigator.navigate(PageListScreenDestination(diary)) })
            }
        }
    }
}

@Composable
fun DiaryItem(diary: Diary, onClick: (Diary) -> Unit) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val date = inputFormat.parse(diary.updatedAt)
    val formattedDate = date?.let { outputFormat.format(it) } ?: diary.updatedAt

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick(diary) }
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
                    model = diary.plant.imageUrl,
                    contentDescription = "Image description",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.background(color = Color.White)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 94.dp)) {
            Text(
                text = diary.title,
                fontSize = 18.sp,
                color = Color(0, 176, 80),
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Fecha: $formattedDate",
                color = Color.White,
                maxLines = 1
            )
        }
    }
}

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fyp.app.data.api.SicknessServiceImp
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.data.model.db.User
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
fun AffectedSicknessListScreen(navigator: DestinationsNavigator, user: User) {

    val affectedSickness = remember { mutableStateListOf<Sickness>() }

    LaunchedEffect(Unit) {
        val result = user.affectedSickness.mapNotNull { sicknessId ->
            withContext(Dispatchers.IO) {
                try {
                    SicknessServiceImp.getInstance().getSicknessById(sicknessId)
                } catch (e: Exception) {
                    Log.e("AffectedSicknessListScreen", "Error loading sickness", e)
                    null
                }
            }
        }
        affectedSickness.clear()
        affectedSickness.addAll(result)
    }

    Column {
        HeaderSection(navigator)
        LazyColumnList(
            items = affectedSickness
        ) { sickness ->
            ContainerIllness(sickness, onClick = {
                navigator.navigate(IllnessDetailsScreenDestination(sickness))
            })
        }
    }
}
package com.fyp.app.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.fyp.app.data.api.SicknessServiceImp
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.data.model.db.User
import com.fyp.app.ui.components.ContainerIllness
import com.fyp.app.ui.components.HeaderSection
import com.fyp.app.ui.components.LazyColumnList
import com.fyp.app.ui.screens.destinations.IllnessDetailsScreenDestination
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
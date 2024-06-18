package com.fyp.app.ui.screens.illness

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.R
import com.fyp.app.data.api.SicknessServiceImp
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.ui.components.BoxLongText
import com.fyp.app.ui.components.DetailBackground
import com.fyp.app.ui.components.OverlayImageWithClick
import com.fyp.app.utils.UserPreferencesImp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
@Destination
fun IllnessDetailsScreen(
    navigator: DestinationsNavigator,
    sickness: Sickness
) {
    DetailBackground {
        LazyColumn {
            item { IllnessHeader(sickness) }
            item { IllnessCareSection(sickness) }
        }
    }

}

@Composable
fun IllnessHeader(sickness: Sickness) {
    val status = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            try {
                SicknessServiceImp.getInstance().statusAffectedSickness(sickness.id)
            } catch (e: Exception) {
                false
            }
        }
        status.value = result
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.5f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                if (UserPreferencesImp.isAuthenticated()) {
                    OverlayImageWithClick(
                        defaultImageUrl = sickness.imageUrl,
                        clickedImageUrl = if (status.value) R.drawable.hearth else R.drawable.hearth_empty,
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                val result = try {
                                    SicknessServiceImp.getInstance()
                                        .updateAffectedSickness(sickness.id)
                                } catch (e: Exception) {
                                    false
                                }
                                withContext(Dispatchers.Main) {
                                    status.value = result
                                }
                            }
                        })
                } else {
                    AsyncImage(
                        model = sickness.imageUrl,
                        contentDescription = "Image description",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = MaterialTheme.shapes.medium)
                            .border(
                                width = 2.0.dp,
                                color = Color.Black,
                                shape = MaterialTheme.shapes.medium
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(0.5f)) {
            Text(
                text = sickness.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
            Text(
                text = sickness.description,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black
            )
        }
    }
}

@Composable
fun IllnessCareSection(sickness: Sickness) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Cuidado recomendado",
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    BoxLongText(text = sickness.treatment)
}










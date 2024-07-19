package com.fyp.app.ui.screens.illness

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.fyp.app.BuildConfig
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .paint(
                    painterResource(id = R.drawable.virus_top),
                    alignment = AbsoluteAlignment.TopRight,
                    contentScale = ContentScale.FillWidth
                )
                .border(
                    width = 3.0.dp,
                    color = Color(59, 170, 0, 255),
                    shape = RoundedCornerShape(20.dp),
                )
        ) {
            item {
                Spacer(modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth())
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .background(Color(226, 237, 169, 255), shape = RoundedCornerShape(20.dp))
                        .zIndex(0f)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        IllnessHeader(sickness)
                        IllnessCareSection(sickness)
                    }
                }
            }

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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-80).dp),
        contentAlignment = Alignment.TopCenter
    ) {
        if (UserPreferencesImp.isAuthenticated()) {
            OverlayImageWithClick(
                defaultImageUrl = if(sickness.imageUrl.contains("https") || sickness.imageUrl.contains("http")) sickness.imageUrl else BuildConfig.BACKEND_URL + sickness.imageUrl,
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
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .border(
                        width = 2.0.dp,
                        color = Color.Black,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .zIndex(2f),
                contentScale = ContentScale.Crop
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-70).dp),
        contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = sickness.name,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black,
            )
            Text(
                text = sickness.description,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun IllnessCareSection(sickness: Sickness) {
    Text(
        text = "Cuidado recomendado",
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
    )
    HorizontalDivider(
        thickness = 2.dp,
        color = Color(59, 170, 0, 255),
        modifier = Modifier.padding(4.dp)
    )
    BoxLongText(text = sickness.treatment)
}










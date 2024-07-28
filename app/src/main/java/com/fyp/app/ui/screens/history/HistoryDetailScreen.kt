package com.fyp.app.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.fyp.app.R
import com.fyp.app.data.model.db.Difficulty
import com.fyp.app.data.model.db.History
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.ui.components.DetailBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.launch

@Composable
@Destination
fun HistoryDetailsScreen(
    navigator: DestinationsNavigator,
    history: History
) {
    val plant: Plant? = history.plant
    val sickness: Sickness? = history.sickness
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { if (sickness == null) 1 else 2 })
    val coroutineScope = rememberCoroutineScope()

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
                Spacer(
                    modifier = Modifier
                        .height(130.dp)
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(226, 237, 169, 255), shape = RoundedCornerShape(20.dp))
                        .zIndex(0f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-80).dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        AsyncImage(
                            model = history.image,
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
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {

                        Spacer(modifier = Modifier.height(64.dp))

                        plant?.let {
                            Text(
                                text = it.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = it.description,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        if (sickness != null) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        Color(0xFF2D6A4F),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Warning,
                                            contentDescription = "Alert Icon",
                                            tint = Color.White,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Potential Disease: ${sickness.name}",
                                            fontSize = 14.sp,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            ) {
                                TabRow(selectedTabIndex = pagerState.currentPage,
                                    modifier = Modifier,
                                    divider = {
                                        HorizontalDivider(
                                            color = Color(59, 170, 0, 255),
                                        )
                                    },
                                    indicator = { tabPositions ->
                                        SecondaryIndicator(
                                            modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                                            color = Color(59, 170, 0, 255),
                                        )
                                    }
                                ) {
                                    listOf("Cuidado de la Planta", "Tratamiento de la Enfermedad").forEachIndexed { index, title ->
                                        Tab(
                                            text = { Text(title, color = Color.Black) },
                                            selected = pagerState.currentPage == index,
                                            onClick = {
                                                coroutineScope.launch {
                                                    pagerState.animateScrollToPage(index)
                                                }
                                            },
                                            modifier = Modifier
                                                .background(Color(226, 237, 169, 255))
                                        )
                                    }
                                }
                                HorizontalPager(state = pagerState, modifier = Modifier.height(400.dp)) { page ->
                                    when (page) {
                                        0 -> PlantDetailsTab(navigator, plant)
                                        1 -> SicknessTreatmentTab(sickness)
                                    }
                                }
                            }
                        } else {
                            // Mostrar solo la información de la planta cuando no hay enfermedad
                            PlantDetailsTab(navigator, plant)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SicknessTreatmentTab(sickness: Sickness?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        item {
            Box(modifier = Modifier
                .fillMaxWidth()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Tratamiento",
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
                    sickness?.treatment?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlantDetailsTab(navigator: DestinationsNavigator, plant: Plant?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        item {
            Box {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Cuidado",
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
                    plant?.treatment?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistoryDetailsScreen() {
    val plant = Plant(
        id = "1",
        name = "Ficus",
        scientificName = "Ficus benjamina",
        description = "A popular houseplant known for its glossy leaves.",
        imageUrl = "https://example.com/ficus.jpg",
        difficulty = Difficulty.EASY,
        treatment = "Water regularly and provide bright, indirect light.",
        characteristics = listOf(),
        sicknesses = listOf()
    )

    val sickness = Sickness(
        id = "1",
        name = "Root Rot",
        description = "A fungal disease that affects the roots of plants.",
        imageUrl = "https://example.com/root_rot.jpg",
        treatment = "Ensure proper drainage and reduce watering.",
    )

    val history = History(
        createdAt = "2024-07-26",
        image = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fes.wikipedia.org%2Fwiki%2FAlgod%25C3%25B3n&psig=AOvVaw1AZcpvx99YcycencYEOHmr&ust=1722080748945000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCJiM4fDQxIcDFQAAAAAdAAAAABAE",
        plant = plant,
        sickness = sickness
    )

    HistoryDetailsScreen(
        navigator = EmptyDestinationsNavigator, // Use an empty navigator for preview
        history = history
    )
}











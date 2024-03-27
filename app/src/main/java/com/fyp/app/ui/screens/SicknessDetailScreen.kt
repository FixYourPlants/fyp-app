package com.fyp.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.R
import com.fyp.app.ui.components.BoxTag
import com.fyp.app.ui.components.image.OverlayImageWithClick

data class Sickness(
    val name: String,
    val description: String,
    val imageUrl: Int,
    val characteristics: List<String>,
    val treatments: List<String>,
    val sickness: List<String>,
    val opinions: List<Opinion>,
    val scienceName: String
)

val sicknessDetails = Sickness(
    name = "Sickness name",
    description = "Sickness description",
    imageUrl = R.drawable.sickness,
    characteristics = listOf(
        "Characteristic 1",
        "Characteristic 2",
        "Characteristic 3"
    ),
    treatments = listOf(
        "Treatment 1",
        "Treatment 2",
        "Treatment 3"
    ),
    sickness = listOf(
        "Plantita 1",
        "Plantita 2",
        "Plantita 3"
    ),
    opinions = listOf(
        Opinion(
            title = "Opinion 1",
            description = "Opinion description",
            userName = "User name"
        ),
        Opinion(
            title = "Opinion 2",
            description = "Opinion description",
            userName = "User name"
        ),
        Opinion(
            title = "Opinion 3",
            description = "Opinion description",
            userName = "User name"

        )
    ),
    scienceName = "Science name"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SicknessDetailsScreen(
    sickness: Sickness
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(2.dp)
            .background(Color(0xFF000500))
            .padding(2.dp)
            .background(Color(0xFF4CAF50))
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        // Fondo: Imagen de la planta
                        Image(
                            painter = painterResource(id = sickness.imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Text(
                        text = sickness.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                    Text(
                        text = sickness.scienceName,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 8.dp),
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

        item {
            Spacer(modifier = Modifier.height(16.dp))
            BoxTag(name = "Características", values = sickness.characteristics)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cuidado recomendado",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(2.dp),
                color = Color(0xFFA5FFA9)
            ) {
                Column {
                    sickness.treatments.forEach { treatment ->
                        Text(
                            text = "• $treatment",
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = Color.Black
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            BoxTag(name = "Plantas:", values = sickness.sickness)
        }
    }
}




@Composable
@Preview
fun SicknessDetailsScreenPreview() {
    SicknessDetailsScreen(sickness = sicknessDetails)
}







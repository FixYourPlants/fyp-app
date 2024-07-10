package com.fyp.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.window.Dialog
import com.fyp.app.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.fyp.app.data.model.db.Difficulty
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.Sickness

@Composable
fun ScannerResult(plant: Plant, sickness: Sickness, onClickPlant: () -> Unit = {}, onClickSickness: () -> Unit = {}) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color(0xFFD8F3DC), shape = RoundedCornerShape(8.dp)) // Verde claro
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = plant.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = plant.scientificName,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AsyncImage(model = plant.imageUrl,
                        contentDescription = plant.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .border(
                                width = 1.0.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(8.dp),
                            )
                            .clickable { onClickPlant() }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = plant.description,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFF2D6A4F),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(8.dp)
                            .clickable { onClickSickness() }
                    ) {
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
                                text = "Potential Disease: ${sickness.name}\n${sickness.description}",
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewScannerResult() {
    val sickness = Sickness(
        id = "1",
        name = "Powdery Mildew",
        description = "This plant may be affected by powdery mildew, a common fungal disease that can cause white, powdery spots on the leaves and stems.",
        imageUrl = "",
        treatment = "Apply fungicide and ensure proper air circulation."
    )

    val plant = Plant(
        id = "1",
        name = "Sunflower",
        scientificName = "Helianthus annuus",
        description = "Sunflowers are large, daisy-like flowers with bright yellow petals. They are known for their ability to track the sun throughout the day.",
        imageUrl = "https://m.media-amazon.com/images/I/61KTbjfuNIL._AC_UF894,1000_QL80_.jpg",
        difficulty = Difficulty.EASY,
        treatment = "Water regularly and provide full sun.",
        characteristics = listOf(),
        sicknesses = listOf(sickness)
    )

    ScannerResult(plant = plant, sickness = sickness)
}
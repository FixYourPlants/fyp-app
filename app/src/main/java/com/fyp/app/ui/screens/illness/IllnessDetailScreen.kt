package com.fyp.app.ui.screens.illness

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.ui.components.BoxTag
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun IllnessDetailsScreen(
    navigator: DestinationsNavigator,
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









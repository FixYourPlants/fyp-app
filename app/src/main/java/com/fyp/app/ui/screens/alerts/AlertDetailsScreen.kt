package com.fyp.app.ui.screens.alerts

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.data.api.AlertServiceImp
import com.fyp.app.data.model.db.Alert
import com.fyp.app.data.model.db.Sickness
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
@Destination
fun AlertDetailsScreen(
    navigator: DestinationsNavigator,
    alert: Alert
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
        item { AlertHeader(alert) }
    }
}

@Composable
fun AlertHeader(alert: Alert) {
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
                AsyncImage(
                    model = alert.image,
                    contentDescription = "Image description",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(0.5f)) {
            Text(
                text = alert.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
            Text(
                text = alert.info["Familia"]!!,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black
            )
            Text(
                text = alert.info["Distribución"]!!,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black
            )
            Text(
                text = alert.info["Hospedantes"]!!,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black
            )
            Text(
                text = alert.info["Daños"]!!,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black
            )
        }
    }
}
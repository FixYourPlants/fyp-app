package com.fyp.app.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.R
import com.fyp.app.data.model.db.Alert
import com.fyp.app.data.model.db.History
import com.fyp.app.data.model.db.Page
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.data.model.db.obtainDifficulty
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ContainerIllness(sickness: Sickness, onClick: () -> Unit) {

    Box(contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .clickable { onClick() }
            .background(color = Color(146, 208, 80))
            .border(width = 0.5.dp, color = Color.Black)
            .paint(painter = painterResource(id =R.drawable.fondo_listado_enfermedades3), contentScale = ContentScale.FillBounds),
    ){
        Column(Modifier.padding(horizontal = 5.dp)) {
            Surface(
                modifier = Modifier
                    .size(84.dp)
                    .border(width = 1.dp, color = Color.Black)
            ) {
                Log.d("ContainerIllness", "Image URL: ${sickness.imageUrl}")
                AsyncImage(
                    model = sickness.imageUrl,
                    contentDescription = "Image description",
                    contentScale= ContentScale.Crop,
                    modifier = Modifier
                        .background(color = Color.White)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 94.dp)){
            Text(
                text = sickness.name,
                fontSize = 18.sp,
                color = Color(0,176,80),
                fontWeight = FontWeight.Bold,
                maxLines=1)
            Text(text = sickness.description,
                color = Color(203, 255, 173, 255),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 3)
        }
    }
}

@Composable
fun ContainerHistory(history: History, onClick: () -> Unit) {
    val plant = history.plant
    val sickness = history.sickness

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .clickable { onClick() }
            .background(color = Color(146, 208, 80))
            .border(width = 0.5.dp, color = Color.Black)
            .paint(painter = painterResource(id = R.drawable.fondo_listado_enfermedades3), contentScale = ContentScale.FillBounds),
    ) {
        Row(modifier = Modifier.padding(horizontal = 5.dp)) {
            // Image for the History
            Surface(
                modifier = Modifier
                    .size(84.dp)
                    .border(width = 1.dp, color = Color.Black)
            ) {
                Log.d("ContainerHistory", "Image URL: ${history.image}")
                AsyncImage(
                    model = history.image,
                    contentDescription = "History image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(color = Color.White)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                plant?.let {
                    Text(
                        text = "Planta: ${it.name}",
                        fontSize = 18.sp,
                        color = Color(0, 176, 80),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                sickness?.let {
                    Text(
                        text = "Enfermedad: ${it.name}",
                        fontSize = 16.sp,
                        color = Color(203, 255, 173, 255),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = history.createdAt,
                    fontSize = 14.sp,
                    color = Color(203, 255, 173, 255),
                    maxLines = 1
                )
            }
        }
    }
}


@Composable
fun ContainerPlagueGob(alert: Alert, onClick: () -> Unit) {
    val imageUrl = if (alert.image == "No image") R.drawable.grasshopper else alert.image
    Log.d("COMPLETE ALERT",imageUrl.toString())

    Box(contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .clickable { onClick() }
            .background(color = Color(146, 208, 80))
            .border(width = 0.5.dp, color = Color.Black)
            .paint(painter = painterResource(id =R.drawable.fondo_listado_plagas), contentScale = ContentScale.FillBounds),
    ){
        Column(Modifier.padding(horizontal = 5.dp)) {
            Surface(
                modifier = Modifier
                    .size(84.dp)
                    .border(width = 1.dp, color = Color.Black)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Plague",
                    contentScale= ContentScale.Crop,
                    modifier = Modifier
                        .background(color = Color.White)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 94.dp)){
            Text(
                text = alert.title,
                fontSize = 16.sp,
                color = Color(0,176,80),
                fontWeight = FontWeight.Bold)
            Text(text = alert.host,
                color = Color(203, 255, 173, 255),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 3)
        }
    }
}

@Composable
fun ContainerPlants(plant: Plant, onClick: (Plant) -> Unit) {
    Box(contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .clickable { onClick(plant) }
            .background(color = Color(146, 208, 80))
            .border(width = 0.5.dp, color = Color.Black)
            .paint(painter = painterResource(id =R.drawable.fondo_listado_plantas3), contentScale = ContentScale.FillBounds),
        ){
        Column(Modifier.padding(horizontal = 5.dp)) {
            Surface(
                modifier = Modifier
                    .size(84.dp)
                    .border(width = 1.dp, color = Color.Black)
            ) {
                AsyncImage(
                    model = plant.imageUrl,
                    contentDescription = "Image description",
                    contentScale= ContentScale.Crop,
                    modifier = Modifier
                        .background(color = Color.White)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 94.dp)){
            Text(
                text = plant.name,
                fontSize = 18.sp,
                color = Color(0,176,80),
                fontWeight = FontWeight.Bold,
                maxLines=1)
            Text(text = plant.description,
                maxLines = 3,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(203, 255, 173, 255),
                overflow = TextOverflow.Ellipsis,
            )

            Log.d("ContainerPlants", "Difficulty: ${plant.difficulty}, Name: ${plant.name}")
            Text(text = "Dificultad: "+ obtainDifficulty(plant.difficulty),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines=1)
        }
    }
}


@Composable
fun PageItem(page: Page, onClick: (Page) -> Unit) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val date = inputFormat.parse(page.createdAt)
    val formattedDate = date?.let { outputFormat.format(it) } ?: page.createdAt

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick(page) }
            .background(color = Color(146, 208, 80))
            .border(width = 0.5.dp, color = Color.Black)
            .paint(painter = painterResource(id =R.drawable.fondo_listado_plantas3), contentScale = ContentScale.FillBounds),

        ) {
        Column(Modifier.padding(horizontal = 5.dp)) {
            Surface(
                modifier = Modifier
                    .requiredSize(84.dp)
                    .border(width = 1.dp, color = Color.Black)
            ) {
                AsyncImage(
                    model = page.imageUrl,
                    contentDescription = "Image description",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.background(color = Color.White)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 94.dp)) {
            Text(
                text = page.title,
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

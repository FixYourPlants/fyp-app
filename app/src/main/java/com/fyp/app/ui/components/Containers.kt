package com.fyp.app.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.BuildConfig
import com.fyp.app.R
import com.fyp.app.data.model.db.Difficulty
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.data.model.db.obtainDifficulty
import org.jetbrains.annotations.Async

@Composable
fun ContainerIllness(sickness: Sickness, onClick: () -> Unit) {

    Box(contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .clickable { onClick() }
            .background(color = Color(146, 208, 80))
            .border(width = 0.5.dp, color = Color.Black)){
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
                maxLines = 4)
        }
    }
}

@Composable
fun ContainerPlague(title:String, image: String, description:String) {

    Box(contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
            .background(color = Color(146, 208, 80))
            .border(width = 0.5.dp, color = Color.Black)){
        Column(Modifier.padding(horizontal = 5.dp)) {
            Surface(
                modifier = Modifier
                    .size(84.dp)
                    .border(width = 1.dp, color = Color.Black)
            ) {
                Image(painter = painterResource(id = R.drawable.grasshopper),
                    contentDescription = "Image description",
                    contentScale= ContentScale.Crop,
                    modifier = Modifier
                        .background(color = Color.White)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 94.dp)){
            Text(
                text = title,
                fontSize = 18.sp,
                color = Color(0,176,80),
                fontWeight = FontWeight.Bold,
                maxLines=1)
            Text(text = description,
                maxLines = 4)
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
            .border(width = 0.5.dp, color = Color.Black),
            // .paint(painter = painterResource(id =R.drawable.fondo_listado_plantas3) contentScale = ContentScale.FillBounds)
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
            Box{
                Text(text = plant.description,
                    maxLines = 3,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(drawStyle = Stroke(width = 2f, miter = 2f, join= StrokeJoin.Round))
                )
                Text(text = plant.description,
                    maxLines = 3,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(text = "Dificultad: "+ obtainDifficulty(plant.difficulty),
                color = Color.White,
                maxLines=1)
        }
    }
}
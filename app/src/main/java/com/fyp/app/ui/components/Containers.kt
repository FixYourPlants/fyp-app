package com.fyp.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.R
import com.fyp.app.data.model.db.Difficulty
import com.fyp.app.data.model.db.obtainDifficulty

@Composable
fun ContainerIllness(title:String, image: String, description:String) {

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
                Image(painter = painterResource(id = R.drawable.virus),
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
fun ContainerPlants(title:String, image: String, difficulty: Difficulty, description:String) {

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
                Image(painter = painterResource(id = R.drawable.plants),
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
                maxLines = 3)
            Text(text = "Dificultad: "+ obtainDifficulty(difficulty),
                color = Color.White,
                maxLines=1)
        }
    }
}


@Preview()
@Composable
fun ContainerPlantsPreview() {
    ContainerPlants("Soy una planta bb","image", Difficulty.EASY, "Soy una plantita y me voy a morir de sed. Necesito que me riegues ya para sobrevivir.")
}
@Preview()
@Composable
fun ContainerPlantsIllness() {
    ContainerIllness("Coronavirus plantil","image", "Le quita el sentido del gusto a tus plantas :(.")
}

@Preview()
@Composable
fun ContainerPlaguePreview() {
    ContainerPlague("La Langosta come hombres","image","Mi nombre es Iñigo Montoya y tu mataste a mi padre. Prepárate a morir (En idioma de insectos).")
}
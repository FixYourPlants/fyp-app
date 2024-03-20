package com.example.myapplication.domain.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fyp.app.R
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.components.buttons.ButtonAndImage


@Composable
fun HomeScreen(navController: NavController) {
    Header()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFF804000))
            .wrapContentSize(Alignment.BottomCenter)
    ) {
        Image(
            painter = painterResource(id = R.drawable.up),
            contentDescription = null,
            modifier = Modifier
                // .scale(1.243f)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .paint(painterResource(id = R.drawable.middle), contentScale = ContentScale.FillBounds)
        ) {
            ButtonAndImage(
                buttonText = "Enciclopedia de Virus",
                imageResourceId = R.drawable.virus,
                onClick = { navController.navigate("viruses") }
            )

            ButtonAndImage(
                buttonText = "Anuncios de Plagas",
                imageResourceId = R.drawable.grasshopper,
                onClick = { navController.navigate("pests") }
            )
        }
        Image(
            painter = painterResource(id = R.drawable.down),
            contentDescription = null,
            modifier = Modifier
                // .scale(1.243f)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .paint(
                    painterResource(id = R.drawable.middle),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            ButtonAndImage(
                buttonText = "Enciclopedia de plantas",
                imageResourceId = R.drawable.notes,
                onClick = { navController.navigate("plants") }
            )

            ButtonAndImage(
                buttonText = "Tus Diarios",
                imageResourceId = R.drawable.virus,
                onClick = { navController.navigate("diary") }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.down),
            contentDescription = null,
            modifier = Modifier
                // .scale(1.243f)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .paint(
                    painterResource(id = R.drawable.middle),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            ButtonAndImage(
                buttonText = "Ayuda",
                imageResourceId = R.drawable.help,
                onClick = { navController.navigate("help") }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.down_down),
            contentDescription = null,
            modifier = Modifier
                // .scale(1.243f)
                .align(Alignment.CenterHorizontally)
        )
    }
}





package com.example.myapplication.domain.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fyp.app.R


@Composable
fun HomeScreen() {
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
                imageResourceId = R.drawable.virus
            )

            ButtonAndImage(
                buttonText = "Anuncios de Plagas",
                imageResourceId = R.drawable.grasshopper
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
                imageResourceId = R.drawable.notes
            )

            ButtonAndImage(
                buttonText = "Tus Diarios",
                imageResourceId = R.drawable.virus
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
                imageResourceId = R.drawable.help
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


@Composable
private fun ButtonAndImage(buttonText: String, imageResourceId: Int) {
    Button(
        onClick = { /* Handle click */ },
        modifier = Modifier
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(buttonText)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                Text(text = "Fix Your Plants", modifier = Modifier.align(Alignment.CenterVertically))
            }
        },
        actions = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Green,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    )
}


@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header()
}
package com.example.myapplication.domain.home.screens

import android.util.Log
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
import com.destinations.HelpScreenDestination
import com.destinations.HomeScreenDestination
import com.destinations.IllnessListScreenDestination
import com.destinations.PlaguesListScreenDestination
import com.destinations.PlantListScreenDestination
import com.destinations.UserDetailsScreenDestination
import com.fyp.app.R
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.components.buttons.ButtonAndImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@RootNavGraph(start = true)
@Destination
fun HomeScreen(navigator: DestinationsNavigator) {
    Column{
        Header(
            onClickLogo = {
                navigator.navigate(HomeScreenDestination())
            },
            onClickAccount = {
                navigator.navigate(UserDetailsScreenDestination())
            }
        )
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
                    onClick = {
                        Log.d("HomeScreen", "Enciclopedia de Virus")
                        navigator.navigate(IllnessListScreenDestination())
                    }
                )

                ButtonAndImage(
                    buttonText = "Anuncios de Plagas",
                    imageResourceId = R.drawable.grasshopper,
                    onClick = {
                        navigator.navigate(PlaguesListScreenDestination())
                    }
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
                    onClick = {
                        navigator.navigate(PlantListScreenDestination())
                    }
                )

                ButtonAndImage(
                    buttonText = "Tus Diarios",
                    imageResourceId = R.drawable.virus,
                    onClick = {
                    }
                )
            }

            Image(
                painter = painterResource(id = R.drawable.down),
                contentDescription = null,
                modifier = Modifier
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
                    onClick = {
                        navigator.navigate(HelpScreenDestination())
                    }
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
}
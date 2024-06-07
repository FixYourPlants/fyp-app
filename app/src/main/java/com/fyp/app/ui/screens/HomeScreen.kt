package com.fyp.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import com.fyp.app.R
import com.fyp.app.ui.components.HeaderSection
import com.fyp.app.ui.components.Scanner
import com.fyp.app.ui.components.buttons.ButtonAndImage
import com.fyp.app.ui.screens.destinations.AlertsListScreenDestination
// import com.fyp.app.ui.screens.destinations.AlertsListScreenDestination
import com.fyp.app.ui.screens.destinations.HelpScreenDestination
import com.fyp.app.ui.screens.destinations.IllnessListScreenDestination
import com.fyp.app.ui.screens.destinations.PlantListScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@RootNavGraph(start = true)
@Destination
fun HomeScreen(navigator: DestinationsNavigator) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            HeaderSection(navigator = navigator)
            Scanner(onClick = { /*navigator.navigate() */ })
            ContentColumn(navigator)
        }
    }
}


@Composable
fun ContentColumn(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFF804000))
            .wrapContentSize(Alignment.BottomCenter)
    ) {

        CenteredImage(R.drawable.up)

        ButtonAndImageRow(
            firstButtonText = "Enciclopedia de Virus",
            firstImageResourceId = R.drawable.virus,
            firstButtonClick = { navigator.navigate(IllnessListScreenDestination()) },
            secondButtonText = "Anuncios de Plagas",
            secondImageResourceId = R.drawable.grasshopper,
            secondButtonClick = { navigator.navigate(AlertsListScreenDestination()) }
        )

        CenteredImage(R.drawable.down)

        ButtonAndImageRow(
            firstButtonText = "Enciclopedia de Plantas",
            firstImageResourceId = R.drawable.plants,
            firstButtonClick = { navigator.navigate(PlantListScreenDestination()) },
            secondButtonText = "Ayuda",
            secondImageResourceId = R.drawable.help,
            secondButtonClick = {
                navigator.navigate(HelpScreenDestination())
            }
        )

        CenteredImage(R.drawable.down_down)
    }
}

@Composable
fun CenteredImage(resourceId: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .paint(painterResource(id = resourceId), contentScale = ContentScale.FillBounds)
    ) {}
}

@Composable
fun ButtonAndImageRow(
    firstButtonText: String,
    firstImageResourceId: Int,
    firstButtonClick: () -> Unit,
    secondButtonText: String,
    secondImageResourceId: Int,
    secondButtonClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .paint(painterResource(id = R.drawable.middle), contentScale = ContentScale.FillBounds)
    ) {
        ButtonAndImage(
            buttonText = firstButtonText,
            imageResourceId = firstImageResourceId,
            onClick = firstButtonClick
        )

        ButtonAndImage(
            buttonText = secondButtonText,
            imageResourceId = secondImageResourceId,
            onClick = secondButtonClick
        )
    }
}


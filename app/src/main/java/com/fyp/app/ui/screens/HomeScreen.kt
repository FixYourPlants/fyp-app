package com.fyp.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fyp.app.R
import com.fyp.app.data.api.PlantServiceImp
import com.fyp.app.data.model.db.History
import com.fyp.app.ui.components.HeaderSection
import com.fyp.app.ui.components.Scanner
import com.fyp.app.ui.components.ScannerResult
import com.fyp.app.ui.components.buttons.ButtonAndImage
import com.fyp.app.ui.screens.destinations.AlertsListScreenDestination
import com.fyp.app.ui.screens.destinations.HelpScreenDestination
import com.fyp.app.ui.screens.destinations.IllnessDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.IllnessListScreenDestination
import com.fyp.app.ui.screens.destinations.PlantDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.PlantListScreenDestination
import com.fyp.app.ui.screens.destinations.PredictCameraScreenDestination
import com.fyp.app.utils.UserPreferencesImp
import com.fyp.app.viewmodel.camera.PredictCameraViewModelImp
import com.fyp.app.viewmodel.camera.toMultipartBodyPart
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@RootNavGraph(start = true)
@Destination
fun HomeScreen(navigator: DestinationsNavigator) {
    val isLoading = remember { mutableStateOf(false) }
    val viewModel = remember { PredictCameraViewModelImp.getInstance() }
    val resultScanner: MutableState<History?> = remember { mutableStateOf(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            HeaderSection(navigator = navigator)
            if (UserPreferencesImp.isAuthenticated()) {
                Scanner(onClick = {
                    isLoading.value = true
                    navigator.navigate(PredictCameraScreenDestination)
                })

            }


            ContentColumn(navigator)
        }

        if (isLoading.value) {
            LoadingIndicator()
        } else if (viewModel.getSelectedBitmap() != null && !isLoading.value && resultScanner.value != null) {
            val sickness = resultScanner.value!!.sickness
            val plant = resultScanner.value!!.plant
            viewModel.clearBitmaps()
            viewModel.clearSelectedBitmap()

            ScannerResult(
                history = resultScanner.value!!,
                onClickSickness = {
                    if (sickness != null) navigator.navigate(
                        IllnessDetailsScreenDestination(
                            sickness
                        )
                    )
                },
                onClickPlant = {
                    if (plant != null) navigator.navigate(
                        PlantDetailsScreenDestination(plant)
                    )
                })
        }
    }

    LaunchedEffect(Unit) {
        val image = viewModel.getSelectedBitmap()?.toMultipartBodyPart("image")
        if (image != null) {
            resultScanner.value = PlantServiceImp.getInstance().predictPlant(image)
            isLoading.value = false
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ContentColumn(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color(0xFF804000))
            .wrapContentSize(Alignment.BottomCenter)
    ) {

        CenteredImage(R.drawable.up)

        ButtonAndImageRow(
            firstButtonText = "Enciclopedia de Virus",
            firstImageResourceId = R.drawable.virus,
            firstButtonClick = { navigator.navigate(IllnessListScreenDestination()) },
            secondButtonText = "Alertas de Plagas",
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

        HeightImage(R.drawable.down_down)
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
fun HeightImage(resourceId: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
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

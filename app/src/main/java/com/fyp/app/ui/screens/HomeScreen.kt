package com.fyp.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.fyp.app.R
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.components.buttons.ButtonAndImage
import com.fyp.app.ui.screens.destinations.DiariesScreenDestination
import com.fyp.app.ui.screens.destinations.HelpScreenDestination
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.IllnessListScreenDestination
import com.fyp.app.ui.screens.destinations.LoginScreenDestination
import com.fyp.app.ui.screens.destinations.PlaguesListScreenDestination
import com.fyp.app.ui.screens.destinations.PlantListScreenDestination
import com.fyp.app.ui.screens.destinations.SignInScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.fyp.app.utils.UserPreferencesImp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun HomeScreen(navigator: DestinationsNavigator) {
    // Variable de estado para controlar la visibilidad del PopUp
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Header(
                onClickLogo = { navigator.navigate(HomeScreenDestination()) },
                onClickAccount = {
                    try {
                        UserPreferencesImp.getInstance()
                        showDialog = true
                    } catch (e: IllegalStateException) {
                        navigator.navigate(LoginScreenDestination())
                    }

                }
            )
            ContentColumn(navigator)
        }

        // Mostrar el PopUp si showDialog es verdadero
        if (showDialog) {
            Popup(alignment = Alignment.TopEnd, onDismissRequest = { showDialog = false }) {
                Box(
                    modifier = Modifier
                        .padding(
                            top = 48.dp,
                            end = 16.dp
                        ) // Ajusta el padding superior para bajar el PopUp
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Row(modifier = Modifier
                            .clickable { navigator.navigate(UserDetailsScreenDestination()) }
                            .padding(8.dp)) {
                            Icon(
                                painterResource(id = R.drawable.user_details),
                                contentDescription = "Mi Perfil"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Mi Perfil")
                        }
                        HorizontalDivider(modifier = Modifier.width(128.dp))
                        Row(modifier = Modifier
                            .clickable { /* Acción para Mis Plantas */ }
                            .padding(8.dp)) {
                            Icon(
                                painterResource(id = R.drawable.my_plants),
                                contentDescription = "Mis Plantas"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Mis Plantas")
                        }
                        HorizontalDivider(modifier = Modifier.width(128.dp))
                        Row(modifier = Modifier
                            .clickable { /* Acción para Historial */ }
                            .padding(8.dp)) {
                            Icon(
                                painterResource(id = R.drawable.history),
                                contentDescription = "Historial"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Historial")
                        }
                        HorizontalDivider(modifier = Modifier.width(128.dp))
                        Row(modifier = Modifier
                            .clickable { /* Acción para Cambiar Idioma */ }
                            .padding(8.dp)) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Cambiar Idioma")
                        }
                        HorizontalDivider(modifier = Modifier.width(128.dp))
                        Row(modifier = Modifier
                            .clickable { navigator.navigate(DiariesScreenDestination()) }
                            .padding(8.dp)) {
                            Icon(
                                painterResource(id = R.drawable.my_diaries),
                                contentDescription = "Mis Diarios"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Mis Diarios")
                        }
                        HorizontalDivider(modifier = Modifier.width(128.dp))
                        Row(modifier = Modifier
                            .clickable { navigator.navigate(LoginScreenDestination()) }
                            .padding(8.dp)) {
                            // Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar Sesión", tint = Color.Red)
                            Icon(
                                painterResource(id = R.drawable.logout),
                                contentDescription = "Cerrar Sesión"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Cerrar Sesión")

                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { showDialog = false }) {
                            Text("Cerrar")
                        }
                    }
                }
            }
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
            secondButtonClick = { navigator.navigate(PlaguesListScreenDestination()) }
        )

        CenteredImage(R.drawable.down)

        ButtonAndImageRow(
            firstButtonText = "Enciclopedia de Plantas",
            firstImageResourceId = R.drawable.plants,
            firstButtonClick = { navigator.navigate(PlantListScreenDestination()) },
            secondButtonText = "Tus Diarios",
            secondImageResourceId = R.drawable.notes,
            secondButtonClick = {
                navigator.navigate(DiariesScreenDestination())
            }
        )

        CenteredImage(R.drawable.down)

        SingleButtonRow(
            buttonText = "Ayuda",
            imageResourceId = R.drawable.help,
            onClick = { navigator.navigate(HelpScreenDestination()) }
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

@Composable
fun SingleButtonRow(
    buttonText: String,
    imageResourceId: Int,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .paint(painterResource(id = R.drawable.middle), contentScale = ContentScale.FillBounds)
    ) {
        ButtonAndImage(
            buttonText = buttonText,
            imageResourceId = imageResourceId,
            onClick = onClick
        )
    }
}

package com.fyp.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.destinations.HomeScreenDestination
import com.fyp.app.R
import com.fyp.app.data.model.Animation
import com.fyp.app.ui.components.onboarding.OnBoardingPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPagerApi::class)
@Composable
@Destination
fun HelpScreen(navigator: DestinationsNavigator) {
    val items = ArrayList<Animation>()

    items.add(Animation(
        R.raw.welcome,
        "¡FixYourPlants es tu Oasis Verde!",
        "Bienvenida a nuestra aplicación de cuidado de plantas regionales de España."
    ))
    items.add(Animation(
        R.raw.plants,
        "Tu Guía Verde",
        "Descubre el fascinante mundo vegetal y registra tus favoritas para un cuidado óptimo."
    ))
    items.add(Animation(
        R.raw.virus,
        "Protege tus Plantas",
        "¡Protege tus plantas con la mejor defensa contra los virus que amenazan su salud!"
    ))
    items.add(Animation(
        R.raw.scanner,
        "Escaner de Plantas",
        "Descubre cómo mantener tus plantas en perfecto estado con nuestro escáner de diagnóstico en tiempo real."
    ))
    items.add(Animation(
        R.raw.plagues,
        "Alertas de Plagas",
        "¡Evita los desastres de las plagas con nuestro servicio de alerta y prevención de plagas de calidad superior!"
    ))

    val pagerState = rememberPagerState(pageCount = items.size, initialOffscreenLimit = items.size-1, infiniteLoop = false, initialPage = 0)

    OnBoardingPager(item = items, pager = pagerState, modifier = Modifier.fillMaxWidth().background(Color.White), onClickFinalButton = {
        navigator.navigate(HomeScreenDestination())
    })
}
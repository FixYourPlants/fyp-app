package com.fyp.app

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import org.junit.Rule
import org.junit.Test

class SicknessTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getListSickness(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // Actions
        rule.onNode(hasText("Enciclopedia de Virus")).performClick()
        // Checking
        rule.onNode(hasText("Oídio")).assertExists()
        rule.onNode(hasText("Moho")).assertExists()
        rule.onNode(hasText("Mancha bacteriana")).assertExists()
    }

    @Test
    fun getSicknessDetails(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // Actions
        rule.onNode(hasText("Enciclopedia de Virus")).performClick()
        rule.onNode(hasText("Oídio")).performClick()
        // Checking
        rule.onNode(hasText("1. Mantener una buena circulación de aire alrededor de las plantas para reducir la humedad.\r\n2. Aplicar tratamientos con fungicidas a intervalos regulares.")).assertExists()
    }
}
package com.fyp.app

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import org.junit.Rule
import org.junit.Test

class PlagueTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getListPlagues(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // Actions
        rule.onNode(hasText("Alertas de Plagas")).performClick()

        while(!rule.onNode(hasText("Agrilus anxius Gory.")).isDisplayed()){
            rule.waitForIdle()
        }
        // Checking
        rule.onNode(hasText("Agrilus anxius Gory.")).assertExists()
        rule.onNode(hasText("Agrilus planipennis Fairmaire")).assertExists()
        rule.onNode(hasText("Tephritidos no europeos")).assertExists()
    }

    @Test
    fun getPlagueDetails(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // Actions
        rule.onNode(hasText("Alertas de Plagas")).performClick()
        while(!rule.onNode(hasText("Agrilus anxius Gory.")).isDisplayed()){
            rule.waitForIdle()
        }
        rule.onNode(hasText("Tephritidos no europeos")).performClick()
        // Checking
        rule.onNode(hasText("Malus domestica, Aronia arbutifolia, Cotoneaster, Crataegus, Prunus americana, Rosa , Rosaceae.")).assertExists()
    }
}
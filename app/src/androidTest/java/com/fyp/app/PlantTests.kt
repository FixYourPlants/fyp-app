package com.fyp.app

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import okhttp3.internal.wait
import org.junit.Rule
import org.junit.Test

class PlantTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getListPlants(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }


        // Actions
        rule.onNode(hasText("Enciclopedia de Plantas")).performClick()

        // Checking
        rule.onNode(hasText("Algodón")).assertExists()
        rule.onNode(hasText("Pimiento Morrón")).assertExists()
        rule.onNode(hasText("Patata")).assertExists()
    }

    @Test
    fun getPlantDetails(){
        // TODO
    }
}
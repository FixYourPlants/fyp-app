package com.fyp.app

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.test.runTest
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
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // Actions
        rule.onNode(hasText("Enciclopedia de Plantas")).performClick()
        rule.onNode(hasText("Algodón")).performClick()
        // Checking
        rule.onNode(hasText("Gossypium hirsutum")).assertExists()
    }

    @Test
    fun getAddFavorite()= runTest {
        login("admin", "admin")
        rule.waitForIdle()
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }

        // Actions
        rule.onNode(hasText("Enciclopedia de Plantas")).performClick()
        rule.onNode(hasText("Algodón")).performClick()
        rule.onNodeWithTag("favorite").performClick()

        // Checking
        // TODO: Comprobación desde el backend

        //Reversing changes
        rule.onNodeWithTag("favorite").performClick()
    }
}
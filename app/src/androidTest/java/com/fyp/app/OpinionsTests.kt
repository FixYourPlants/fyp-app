package com.fyp.app

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.time.Duration

class OpinionsTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getOpinions(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // Actions
        rule.onNode(hasText("Enciclopedia de Plantas")).performClick()
        rule.onNode(hasText("Pimiento Morrón")).performClick()
        rule.onNode(hasText("Opiniones")).performClick()
        // Checking
        rule.onNode(hasText("Est bueno en un Wok")).assertExists()
    }

    @Test
    fun createOpinion() {
        runBlocking() { // Invalid Inputs
            login("admin", "admin")
            rule.waitForIdle()
            rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }

            // Actions
            rule.onNode(hasText("Enciclopedia de Plantas")).performClick()
            rule.onNode(hasText("Algodón")).performClick()
            rule.onNode(hasText("Opiniones")).performClick()

            rule.onNode(hasText("Añadir Opinión")).performClick()
            rule.onNodeWithText("Título").performTextInput("Prueba")
            rule.waitForIdle()
            rule.onNodeWithText("Descripción").performTextInput("Prueba Descripción más larga por si es culpa del límite de letras")
            rule.waitForIdle()
            rule.onNodeWithText("Añadir").performClick()
            rule.waitForIdle()
            // Checking

//            rule.onAllNodesWithText("Prueba").onFirst().assertExists()
        }
    }

    @Test
    fun createOpinionNegative1() {
        runBlocking() { // Invalid Inputs
            login("admin", "admin")
            rule.waitForIdle()
            rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }

            // Actions
            rule.onNode(hasText("Enciclopedia de Plantas")).performClick()
            rule.onNode(hasText("Algodón")).performClick()
            rule.onNode(hasText("Opiniones")).performClick()

            rule.onNode(hasText("Añadir Opinión")).performClick()
            rule.onNodeWithText("Título").performTextInput("A")
            rule.waitForIdle()
            rule.onNodeWithText("Descripción").performTextInput("B")
            rule.waitForIdle()
            rule.onNodeWithText("Añadir").performClick()

            // Checking
            rule.onNodeWithText("El título debe tener al menos 3 caracteres").assertExists()
            rule.onNodeWithText("La descripción debe tener al menos 5 caracteres").assertExists()
        }
    }

    @Test
    fun createOpinionNegative2(){ // User not logged in
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // Actions
        rule.onNode(hasText("Enciclopedia de Plantas")).performClick()
        rule.onNode(hasText("Algodón")).performClick()
        rule.onNode(hasText("Opiniones")).performClick()
        rule.onNode(hasText("Añadir Opinión")).performClick()
        // Checking
        rule.onNodeWithText("Inicia sesión para añadir una opinión").assertExists()

    }
}
package com.fyp.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PageTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getListPages() = runTest{
        login("admin", "admin")
        rule.waitForIdle()
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }

        // Actions
        rule.onNodeWithTag("profile_pic").performClick()
        rule.onNodeWithText("Mis Diarios").performClick()
        rule.onNodeWithText("Diario para Pimiento Morrón").performClick()

        //Checking
        rule.onNodeWithText("Prueba").assertExists()
    }

    @Test
    fun getPageDetails() = runTest{
        login("admin", "admin")
        rule.waitForIdle()
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // Actions
        rule.onNodeWithTag("profile_pic").performClick()
        rule.onNodeWithText("Mis Diarios").performClick()
        rule.onNodeWithText("Diario para Pimiento Morrón").performClick()
        rule.onNodeWithText("Prueba").performClick()
        //Checking
        rule.onNodeWithText("Prueba").assertExists()
    }

    @Test
    fun createPage() = runTest{
//        login("admin", "admin")
//        rule.waitForIdle()
//        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
//        // Actions
//        rule.onNodeWithTag("profile_pic").performClick()
//        rule.onNodeWithText("Mis Diarios").performClick()
//        rule.onNodeWithText("Diario para Pimiento Morrón").performClick()
//        rule.onNodeWithText("Crear Página").performClick()
//        rule.waitForIdle()
//        rule.onNodeWithText("Título").performTextInput("Prueba Test Andorid")
//        rule.waitForIdle()
//        rule.onNodeWithText("Contenido").performTextInput("Prueba Descripción más larga por si es culpa del límite de letras")
//        rule.onNodeWithText("Guardar").performClick()
        // Checking
    }

    @Test
    fun createPageNegative1()= runTest{ // Invalid Inputs
        login("admin", "admin")
//        rule.waitForIdle()
//        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
//        // Actions
//        rule.onNodeWithTag("profile_pic").performClick()
//        rule.onNodeWithText("Mis Diarios").performClick()
//        rule.onNodeWithText("Diario para Pimiento Morrón").performClick()
//        rule.onNodeWithText("Crear Página").performClick()
//        rule.waitForIdle()
//        rule.onNodeWithText("Título").performTextInput("")
//        rule.waitForIdle()
//        rule.onNodeWithText("Contenido").performTextInput("")
//        rule.waitForIdle()
        // Checking
    }
//    @Test
//    fun createPageNegative2()= runTest{ // Invalid Inputs
//        login("admin", "admin")
//        rule.waitForIdle()
//        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
//        // Actions
//        rule.onNodeWithTag("profile_pic").performClick()
//        rule.onNodeWithText("Mis Diarios").performClick()
//        rule.onNodeWithText("Diario para Pimiento Morrón").performClick()
//        rule.onNodeWithText("Crear Página").performClick()
//        // Checking
//        rule.onNodeWithText("Prueba Test Andorid").assertExists()
//    }
}
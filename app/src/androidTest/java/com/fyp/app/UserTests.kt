package com.fyp.app

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class UserTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun login() = runTest {
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // Actions
        rule.onNodeWithTag("login").performClick()
        rule.onNodeWithText("Nombre de usuario").performTextInput("usertest")
        rule.onNodeWithText("Contraseña").performTextInput("testpass12345")
        rule.onNodeWithText("Iniciar sesión").performClick()
        delay(10000000)
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 100000) {
            rule.onNodeWithText("Enciclopedia de Plantas").isDisplayed()
        }
        // Checking
        rule.onNodeWithText("Enciclopedia de Plantas").assertExists()
    }

    @Test
    fun loginGoogle(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun logout(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun register(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun registerGoogle(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun updateUser(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    // LOS TESTS DE VERIFICAR CONTRASEÑA Y CAMBIAR CONTRASEÑA NO SE HARÁN

}
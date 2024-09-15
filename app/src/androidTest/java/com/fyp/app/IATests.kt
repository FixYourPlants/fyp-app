package com.fyp.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class IATests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getPrediction() = runTest {
        login("admin", "admin")
        rule.waitForIdle()
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }

        // Acciones
        rule.onNodeWithTag("scanner_button").performClick() // Abrir la cámara

        rule.waitForIdle()
    }

    @Test
    fun getPredictionNegative() = runTest { // User is not logged in
        login("admin", "admin")
        rule.waitForIdle()
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }

        // Checking
        rule.onNodeWithTag("scanner_button").assertDoesNotExist()
    }
}
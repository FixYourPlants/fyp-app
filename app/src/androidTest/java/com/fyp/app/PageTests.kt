package com.fyp.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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
    fun createPage(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun createPageNegative1(){ // Invalid Inputs
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun createPageNegative2(){ // Second Page in the Day
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }
}
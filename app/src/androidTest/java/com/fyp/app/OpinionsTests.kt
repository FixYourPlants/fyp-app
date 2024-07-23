package com.fyp.app

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import org.junit.Rule
import org.junit.Test

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
    fun createOpinion(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun createOpinionNegative1(){ // Invalid Inputs
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun createOpinionNegative2(){ // User not logged in
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }
}
package com.fyp.app

import androidx.compose.ui.test.junit4.createComposeRule
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import org.junit.Rule
import org.junit.Test

class IATests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getPrediction(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun getPredictionNegative(){ // User is not logged in
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }
}
package com.fyp.app

import androidx.compose.ui.test.junit4.createComposeRule
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import org.junit.Rule
import org.junit.Test

class SicknessTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getListSickness(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun getSicknessDetails(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }
}
package com.fyp.app

import androidx.compose.ui.test.junit4.createComposeRule
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import org.junit.Rule
import org.junit.Test

class HistoryTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getListHistory(){
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }
}
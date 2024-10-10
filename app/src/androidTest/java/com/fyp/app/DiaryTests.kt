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

class DiaryTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getListDiaries() = runTest {
        login("admin", "admin")
        rule.waitForIdle()
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }

        // Actions
        rule.onNodeWithTag("profile_pic").performClick()
        rule.onNodeWithText("Mis Diarios").performClick()

        //Checking
        rule.onNodeWithText("Diario para Pimiento Morrón").assertExists()
    }
}

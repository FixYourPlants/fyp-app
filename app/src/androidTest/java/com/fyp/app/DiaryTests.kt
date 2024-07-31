package com.fyp.app

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.fyp.app.data.api.TokenServiceImp
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.ui.screens.NavGraphs
import com.fyp.app.utils.UserPreferencesImp
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class DiaryTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getListDiaries() = runTest {
        val responseId = UserServiceImp.getInstance().getUserIdByUsername("admin")

        val data = UserServiceImp.getInstance().loginUserVerified(
            mapOf(
                "username" to "admin",
                "password" to "admin",
                "googleAccount" to "false",
            )
        )

        responseId["user_id"]?.let {
            UserPreferencesImp.initialize("admin", data["refresh"].toString(), data["access"].toString(), it)
        }

        try {
            val token = UserPreferencesImp.getInstance().access
            Log.d("TOKEN", "Token after login: $token")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val user = UserServiceImp.getInstance().getLoggedInUser()

        Log.d("User", user.toString())

        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }

        while (true) {
            try {
                rule.waitForIdle()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

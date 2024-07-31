package com.fyp.app

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.fyp.app.data.api.TokenServiceImp
import com.fyp.app.data.api.UserService
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.ui.screens.NavGraphs
import com.fyp.app.utils.UserPreferencesImp
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DiaryTests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun getListDiaries() = runTest {
        login("admin", "admin")

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

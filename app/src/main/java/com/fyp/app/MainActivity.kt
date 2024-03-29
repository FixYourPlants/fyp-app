
package com.fyp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.domain.home.screens.NavGraphs
import com.fyp.app.ui.theme.OneTapComposeTheme
import com.fyp.app.utils.GlobalErrorHandler
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler(GlobalErrorHandler(applicationContext))
        setContent {
            OneTapComposeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

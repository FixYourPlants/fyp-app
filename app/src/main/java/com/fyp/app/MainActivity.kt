package com.fyp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fyp.app.domain.authenticationGoogle.GoogleAuthUiClient
import com.fyp.app.ui.screens.NavGraphs
import com.google.android.gms.auth.api.identity.Identity
import com.ramcosta.composedestinations.DestinationsNavHost
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DestinationsNavHost(navGraph = NavGraphs.root)
            TokenManager.startTokenRefreshTask(30, TimeUnit.SECONDS)

        }
    }
}
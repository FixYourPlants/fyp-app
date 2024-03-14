package com.fyp.app.domain.authenticationGoogle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fyp.app.domain.authenticationGoogle.screens.LoginGoogleScreen

class LoginGoogleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginGoogleScreen()
        }
    }
}
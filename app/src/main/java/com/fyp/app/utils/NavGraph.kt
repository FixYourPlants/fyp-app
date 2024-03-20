package com.fyp.app.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.domain.authentication.screens.LoginScreen
import com.example.myapplication.domain.home.screens.HomeScreen

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable(route = "home") {
            HomeScreen(navController = navController)
        }
        composable(route = "login") {
            LoginScreen(navController = navController)
        }
    }
}

/*
To navigate to the HomeScreen from the LoginScreen, you can use the following code:

navController.navigate("home")
 */
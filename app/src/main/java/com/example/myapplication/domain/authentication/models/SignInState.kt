package com.example.myapplication.domain.authentication.models

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
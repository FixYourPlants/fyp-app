package com.fyp.app.data.api.responses

data class RegistrationRequest(
    val username: String,
    val email: String,
    val password1: String,
    val password2: String
)

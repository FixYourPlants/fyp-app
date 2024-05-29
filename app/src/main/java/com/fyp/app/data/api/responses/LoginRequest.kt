package com.fyp.app.data.api.responses

data class LoginRequest(
    val username: String,
    val password: String,
    val email: String
)

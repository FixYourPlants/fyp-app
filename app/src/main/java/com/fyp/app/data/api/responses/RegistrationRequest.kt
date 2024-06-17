package com.fyp.app.data.api.responses

data class RegistrationRequest(
    val username: String,
    val email: String,
    val password: String,
    val password2: String,
    val googleAccount: Boolean
)

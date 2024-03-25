package com.fyp.app.data.model

data class Opinion(
    val title: String,
    val description: String,
    val email: String,
    val plant: Plant,
    val user: User
)
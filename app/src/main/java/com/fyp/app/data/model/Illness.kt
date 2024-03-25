package com.fyp.app.data.model

data class Illness(
    val name: String,
    val description: String,
    val imageUrl: String,
    val care: Care,
    val plants: List<Plant>
)
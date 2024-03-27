package com.fyp.app.data.model

data class Plant(
    val name: String,
    val scientificName: String,
    val description: String,
    val imageUrl: String,
    val difficulty: Difficulty,
    val care: Care
)
package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName

data class Sickness(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("treatment")
    val care: String,
)
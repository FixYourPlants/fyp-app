package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName

data class Characteristic(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

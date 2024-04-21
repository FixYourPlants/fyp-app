package com.fyp.app.data.model

import com.google.gson.annotations.SerializedName

data class Characteristic(
    @SerializedName("characteristicId")
    val id:String,
    @SerializedName("name")
    val name: String,
    @SerializedName("plant")
    val plants: List<Plant>
)

package com.fyp.app.data.model

import com.google.gson.annotations.SerializedName

data class Illness(
    @SerializedName("illnessId")
    val id:String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("care")
    val care: Care,
    @SerializedName("plants")
    val plants:List<Plant>
)
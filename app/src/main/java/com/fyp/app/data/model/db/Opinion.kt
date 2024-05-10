package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName

data class Opinion(
    @SerializedName("illnessId")
    val id:String,
    @SerializedName("illnessId")
    val title: String,
    @SerializedName("illnessId")
    val description: String,
    @SerializedName("illnessId")
    val email: String,
    @SerializedName("illnessId")
    val plant: Plant,
    @SerializedName("illnessId")
    val user: User
)
package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreateOpinion(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("plant_id")
    val plantId: String,
    @SerializedName("user")
    val user: User
): Serializable
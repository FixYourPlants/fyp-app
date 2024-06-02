package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Opinion(
    @SerializedName("id")
    val id:String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("plant")
    val plant: Plant,
    @SerializedName("user")
    val user: User
): Serializable
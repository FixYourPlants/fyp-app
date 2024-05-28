package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("pk")
    val id:String,
    @SerializedName("username")
    val username: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("favorite_plant")
    val favoritePlants: List<Plant>
): Serializable

package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    val id:String,
    @SerializedName("username")
    val username: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String = "",
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("favourite_plant")
    val favouritePlants: List<String>,
    @SerializedName("about_me")
    val aboutMe: String
): Serializable

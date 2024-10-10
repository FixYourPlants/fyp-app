package com.fyp.app.data.model.db;

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CreateOpinion(
    @SerializedName("id")
    val id:String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("plant")
    val plant: String,
    @SerializedName("user")
    val user: String
): Serializable
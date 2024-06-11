package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Alert(
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("family")
    val family: String,
    @SerializedName("distribution")
    val distribution: String,
    @SerializedName("host")
    val host: String,
    @SerializedName("damage")
    val damage: String,
): Serializable

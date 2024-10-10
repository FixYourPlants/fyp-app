package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Notification(
    @SerializedName("title")
    val title: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("description")
    val content: String,
): Serializable
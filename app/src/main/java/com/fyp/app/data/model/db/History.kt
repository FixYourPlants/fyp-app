package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class History(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("plant")
    val plant: Plant?,
    @SerializedName("sickness")
    val sickness: Sickness?
): Serializable
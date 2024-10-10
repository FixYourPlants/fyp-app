package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Care(
    @SerializedName("careId")
    val id:String,
    @SerializedName("careType")
    val type: CareType,
    @SerializedName("actions")
    val actions: String
): Serializable
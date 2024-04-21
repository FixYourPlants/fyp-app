package com.fyp.app.data.model

import com.google.gson.annotations.SerializedName

data class Care(
    @SerializedName("careId")
    val id:String,
    @SerializedName("careType")
    val type: CareType,
    @SerializedName("actions")
    val actions: String
)
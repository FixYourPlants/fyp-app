package com.fyp.app.data.api

import com.fyp.app.data.model.db.Plant
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("count")
    val count: String,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: ArrayList<Plant>
)


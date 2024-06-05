package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Page(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("diary")
    val diary: Diary
): Serializable

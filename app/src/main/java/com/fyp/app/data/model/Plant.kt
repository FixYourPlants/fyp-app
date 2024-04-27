package com.fyp.app.data.model

import com.google.gson.annotations.SerializedName

data class Plant(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name: String,
    @SerializedName("scientificName")
    val scientificName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("difficulty")
    val difficulty: Difficulty,
    @SerializedName("care")
    val care: Care,
    @SerializedName("characteristics")
    val characteristics: List<Characteristic>,
    @SerializedName("illness")
    val illness: List<Illness>
)
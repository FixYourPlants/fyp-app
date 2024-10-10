package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Plant(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name: String,
    @SerializedName("scientific_name")
    val scientificName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("difficulty")
    val difficulty: Difficulty,
    @SerializedName("treatment")
    val treatment: String,
    @SerializedName("characteristics")
    val characteristics: List<Characteristic>,
    @SerializedName("sicknesses")
    val sicknesses: List<Sickness>
): Serializable
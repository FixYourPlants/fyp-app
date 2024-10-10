package com.fyp.app.data.model.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Diary(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("plant")
    val plant: Plant,
    @SerializedName("user")
    val user: User
) : Serializable {
    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + plant.hashCode()
        // Excluimos el campo user del cálculo de hashCode
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Diary

        if (id != other.id) return false
        if (title != other.title) return false
        if (updatedAt != other.updatedAt) return false
        if (plant != other.plant) return false
        // Excluimos el campo user de la comparación de igualdad
        return true
    }
}


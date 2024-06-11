package com.fyp.app.utils

import android.util.Log
import com.fyp.app.data.api.TokenServiceImp

data class UserPreferences(
    val email: String,
    val refresh: String,
    val access: String,
    val id: String
)

object UserPreferencesImp {
    private var instance: UserPreferences? = null

    fun initialize(email: String, refresh: String, access: String, id: String) {
        if (instance == null) {
            instance = UserPreferences(email, refresh, access, id)
        }
    }

    fun getInstance(): UserPreferences {
        return instance ?: throw IllegalStateException("UserPreferences not initialized")
    }

    fun clear() {
        instance = null
    }

    fun isAuthenticated(): Boolean {
        return instance != null
    }

    suspend fun refreshToken(): Boolean {
        if (instance == null) {
            Log.e("UserPreferencesImp", "UserPreferences not initialized")
            return false
        }
        val refresh = instance?.refresh ?: throw IllegalStateException("Refresh token not available")

        // Obtener los nuevos tokens usando el servicio TokenServiceImp
        val tokenResponse = TokenServiceImp.getInstance().getSimpleRefresh(mapOf("refresh" to refresh))

        // Actualizar la instancia con los nuevos tokens
        val newAccessToken = tokenResponse["access"] ?: throw IllegalStateException("Access token not available")
        val newRefreshToken = tokenResponse["refresh"] ?: throw IllegalStateException("Refresh token not available")

        instance = instance?.copy(refresh = newRefreshToken, access = newAccessToken)

        return true
    }



}



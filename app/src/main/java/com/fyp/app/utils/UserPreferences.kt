package com.fyp.app.utils

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
        return instance ?: throw IllegalStateException("UserPreferences is not initialized")
    }

    fun clear() {
        instance = null
    }
}



package com.fyp.app.utils

import android.content.Context
import android.content.SharedPreferences

object UserPreferences {
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_EMAIL = "email"
    private const val KEY_TOKEN = "token"
    private const val ID = "id"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(context: Context, email: String, token: String, id: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_TOKEN, token)
        editor.putString(ID, id)
        editor.apply()
    }

    fun getUserEmail(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_EMAIL, null)
    }

    fun getToken(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_TOKEN, null)
    }

    fun clearUser(context: Context) {
        val editor = getSharedPreferences(context).edit()
        editor.clear()
        editor.apply()
    }
}

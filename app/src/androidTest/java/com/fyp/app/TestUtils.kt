package com.fyp.app

import TokenManager
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.utils.UserPreferencesImp
import java.util.concurrent.TimeUnit

suspend fun login(username: String, password: String) {
    TokenManager.startTokenRefreshTask(270, TimeUnit.SECONDS)

    val responseId = UserServiceImp.getInstance().getUserIdByUsername(username)

    val data = UserServiceImp.getInstance().loginUserVerified(
        mapOf(
            "username" to username,
            "password" to password,
            "googleAccount" to "false",
        )
    )

    responseId["user_id"]?.let {
        UserPreferencesImp.initialize(username, data["refresh"].toString(), data["access"].toString(), it)
    }

    TokenManager.resetAllServices()
}
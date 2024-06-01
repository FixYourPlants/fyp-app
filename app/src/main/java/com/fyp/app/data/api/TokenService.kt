package com.fyp.app.data.api

import com.fyp.app.BuildConfig
import com.fyp.app.data.model.db.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {

    @POST("api-token-auth/")
    suspend fun getToken(@Body user: Map<String, String>): User

    @POST("/simple/token")
    suspend fun getSimpleToken(@Body user: Map<String, String>): Map<String, String>

    @POST("/simple/refresh")
    suspend fun getSimpleRefresh(@Body token: Map<String, String>): Map<String, String>


}

object TokenServiceImp {
    private var instance: TokenService? = null

    fun getInstance(): TokenService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BuildConfig.BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TokenService::class.java)
        }
        return instance!!
    }
}
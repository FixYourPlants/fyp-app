package com.fyp.app.data.api

import com.fyp.app.data.model.db.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {

    @POST("api-token-auth/")
    suspend fun getToken(@Body user: User): User


}

object TokenServiceImp {
    private var instance: TokenService? = null

    fun getInstance(): TokenService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TokenService::class.java)
        }
        return instance!!
    }

    private const val BASE_URL = "https://example.com/" // Reemplaza esto con tu URL base
}
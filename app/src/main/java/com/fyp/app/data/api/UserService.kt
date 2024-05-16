package com.fyp.app.data.api

import com.fyp.app.data.model.db.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    @GET("users/list")
    suspend fun getUsers(): List<User>

    @POST("users/create")
    suspend fun addUser(@Body user: User): User

    @GET("user/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): User

    @PUT("users/{userId}")
    suspend fun updateUser(@Path("userId") userId: Int, @Body user: User): User

    @DELETE("users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: Int): User
}

object UserServiceImp {
    private var instance: UserService? = null

    fun getInstance(): UserService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService::class.java)
        }
        return instance!!
    }

    private const val BASE_URL = "http://10.0.2.2:8000/" // Reemplaza esto con tu URL base
}
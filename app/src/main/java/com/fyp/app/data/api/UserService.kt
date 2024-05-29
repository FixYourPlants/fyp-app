package com.fyp.app.data.api

import com.fyp.app.BuildConfig
import com.fyp.app.data.api.responses.LoginRequest
import com.fyp.app.data.api.responses.LoginResponse
import com.fyp.app.data.api.responses.RegistrationRequest
import com.fyp.app.data.model.db.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Dictionary

interface UserService {

    @POST("api-auth/registration/")
    suspend fun registerUser(@Body request: RegistrationRequest)

    @POST("api-auth/login/")
    suspend fun loginUser(@Body response: LoginRequest): LoginResponse

    @GET("api/v1/users/list")
    suspend fun getUsers(): List<User>

    @GET("api/v1/users/list/get_user_id_by_email/")
    suspend fun getUserIdByEmail(@Query("email") username: String): Map<String, String>

    @GET("user/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): User

    @PUT("api/v1/users/{userId}")
    suspend fun updateUser(@Path("userId") userId: Int, @Body user: User): User

    @DELETE("api/v1/users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: Int): User
}

object  UserServiceImp {
    private var instance: UserService? = null

    fun getInstance(): UserService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BuildConfig.BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService::class.java)
        }
        return instance!!
    }
}
package com.fyp.app.data.api

import android.util.Log
import com.fyp.app.BuildConfig
import com.fyp.app.data.api.responses.LoginRequest
import com.fyp.app.data.api.responses.LoginResponse
import com.fyp.app.data.api.responses.RegistrationRequest
import com.fyp.app.data.model.db.User
import com.fyp.app.utils.UserPreferencesImp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @POST("api-auth/registration/")
    suspend fun registerUser(@Body request: RegistrationRequest)

    @POST("api-auth/login/")
    suspend fun loginUser(@Body response: LoginRequest): LoginResponse

    @GET("api/v1/users/list")
    suspend fun getUsers(): List<User>

    @GET("api/v1/users/list/get_user_id_by_username/")
    suspend fun getUserIdByUsername(@Query("username") username: String): Map<String, String>

    @GET("user/{userId}")
    suspend fun getUserById(@Path("userId") userId: String): User

    @PUT("api/v1/users/{userId}")
    suspend fun updateUser(@Path("userId") userId: Int, @Body user: User): User

    @DELETE("api/v1/users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: Int): User

    @GET("api/v1/users/logged/get_logged_in_user/")
    suspend fun getLoggedInUser(): User
}

object  UserServiceImp {
    private var instance: UserService? = null
    private var addedToken = false

    fun getInstance(): UserService {
        try {
            val token = UserPreferencesImp.getInstance().access
            Log.d("token", token)
            val httpClient = OkHttpClient.Builder()
            if (!addedToken) {
                httpClient.addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer $token")
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                instance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BACKEND_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserService::class.java)
                addedToken = true
            }
        } catch (e: Exception) {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BACKEND_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserService::class.java)
            }
        }


        return instance!!
    }
}
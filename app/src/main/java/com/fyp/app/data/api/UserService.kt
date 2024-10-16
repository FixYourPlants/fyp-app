package com.fyp.app.data.api

import com.fyp.app.data.model.RegistrationRequest
import com.fyp.app.data.model.db.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @POST("api/v1/create_user/")
    suspend fun registerUser(@Body request: RegistrationRequest): ResponseBody

    @GET("api/v1/users/list/get_user_id_by_username/")
    suspend fun getUserIdByUsername(@Query("username") username: String): Map<String, String>

    @POST("api/v1/login/")
    suspend fun loginUserVerified(@Body user: Map<String, String>): Map<String, String>


    @Multipart
    @PUT("api/v1/users/{userId}/")
    suspend fun updateUser(
        @Path("userId") userId: String,
        @Part("username") username: RequestBody,
        @Part("first_name") firstName: RequestBody,
        @Part("last_name") lastName: RequestBody,
        @Part("email") email: RequestBody,
        @Part("about_me") aboutMe: RequestBody,
        @Part image: MultipartBody.Part?
    ): User

    @GET("api/v1/users/logged/get_logged_in_user/")
    suspend fun getLoggedInUser(): User
}


object UserServiceImp : BaseService<UserService>(UserService::class.java)
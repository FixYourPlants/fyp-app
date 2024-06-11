package com.fyp.app.data.api

import com.fyp.app.data.model.db.User
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {

    @POST("api-token-auth/")
    suspend fun getToken(@Body user: Map<String, String>): User

    @POST("/simple/token")
    suspend fun getSimpleToken(@Body user: Map<String, String>): Map<String, String>

    @POST("/simple/token/refresh/")
    suspend fun getSimpleRefresh(@Body token: Map<String, String>): Map<String, String>


}


object TokenServiceImp: BaseService<TokenService>(TokenService::class.java)
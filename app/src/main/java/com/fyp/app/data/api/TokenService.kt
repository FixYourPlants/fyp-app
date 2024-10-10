package com.fyp.app.data.api

import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {
    @POST("/simple/token/refresh/")
    suspend fun getSimpleRefresh(@Body token: Map<String, String>): Map<String, String>
}


object TokenServiceImp: BaseService<TokenService>(TokenService::class.java)
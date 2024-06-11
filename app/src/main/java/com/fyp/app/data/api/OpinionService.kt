package com.fyp.app.data.api

import com.fyp.app.data.model.db.CreateOpinion
import com.fyp.app.data.model.db.Opinion
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OpinionService {

    @GET("api/v1/opinions/lists/")
    suspend fun getOpinions(@Query("plant_id") plantId: String? = null): List<Opinion>

    @POST("api/v1/opinions/create/")
    suspend fun addOpinion(@Body opinion: CreateOpinion): CreateOpinion

    @GET("opinion/{opinionId}")
    suspend fun getOpinionById(@Path("opinionId") opinionId: Int): CreateOpinion

    @PUT("opinions/{opinionId}")
    suspend fun updateOpinion(@Path("opinionId") opinionId: Int, @Body opinion: CreateOpinion): CreateOpinion

    @DELETE("opinions/{opinionId}")
    suspend fun deleteOpinion(@Path("opinionId") opinionId: Int): CreateOpinion
}

object OpinionServiceImp: BaseService<OpinionService>(OpinionService::class.java)

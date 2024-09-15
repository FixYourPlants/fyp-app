package com.fyp.app.data.api

import com.fyp.app.data.model.db.CreateOpinion
import com.fyp.app.data.model.db.Opinion
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OpinionService {

    @GET("api/v1/opinions/lists/")
    suspend fun getOpinions(@Query("plant_id") plantId: String? = null): List<Opinion>

    @POST("api/v1/opinions/create/")
    suspend fun addOpinion(@Body opinion: CreateOpinion): CreateOpinion

}

object OpinionServiceImp: BaseService<OpinionService>(OpinionService::class.java)

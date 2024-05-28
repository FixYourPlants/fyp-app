package com.fyp.app.data.api

import com.fyp.app.BuildConfig
import com.fyp.app.data.model.db.Opinion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OpinionService {

    @GET("opinions/list")
    suspend fun getOpinions(): List<Opinion>

    @POST("opinions/create")
    suspend fun addOpinion(@Body opinion: Opinion): Opinion

    @GET("opinion/{opinionId}")
    suspend fun getOpinionById(@Path("opinionId") opinionId: Int): Opinion

    @PUT("opinions/{opinionId}")
    suspend fun updateOpinion(@Path("opinionId") opinionId: Int, @Body opinion: Opinion): Opinion

    @DELETE("opinions/{opinionId}")
    suspend fun deleteOpinion(@Path("opinionId") opinionId: Int): Opinion
}

object OpinionServiceImp {
    private var instance: OpinionService? = null

    fun getInstance(): OpinionService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BuildConfig.BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpinionService::class.java)
        }
        return instance!!
    }
}
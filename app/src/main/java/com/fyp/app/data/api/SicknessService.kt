package com.fyp.app.data.api

import com.fyp.app.BuildConfig
import com.fyp.app.data.model.db.Sickness
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SicknessService {

    @GET("/api/v1/sicknesses/list/")
    suspend fun getSicknesses(): List<Sickness>

    @POST("api/v1/sickness/create")
    suspend fun addSickness(@Body sickness: Sickness): Sickness

    @GET("api/v1/sickness/{sicknessId}/")
    suspend fun getSicknessById(@Path("sicknessId") sicknessId: Int): Sickness

    @POST("api/v1/sicknesses/{sicknessId}")
    suspend fun updateSickness(@Path("sicknessId") sicknessId: Int, @Body sickness: Sickness): Sickness
}

@RefreshableService
object SicknessServiceImp: BaseService<SicknessService>(SicknessService::class.java)
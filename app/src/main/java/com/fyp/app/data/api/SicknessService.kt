package com.fyp.app.data.api

import com.fyp.app.data.model.db.Sickness
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SicknessService {

    @GET("/api/v1/sicknesses/list/")
    suspend fun getSicknesses(): List<Sickness>

    @POST("api/v1/sickness/create")
    suspend fun addSickness(@Body sickness: Sickness): Sickness

    @GET("api/v1/sickness/{sicknessId}/")
    suspend fun getSicknessById(@Path("sicknessId") sicknessId: String): Sickness

    @POST("api/v1/sicknesses/{sicknessId}")
    suspend fun updateSickness(@Path("sicknessId") sicknessId: Int, @Body sickness: Sickness): Sickness

    @GET("api/v1/sickness/affected/")
    suspend fun getAffectedSicknesses(@Path("userId") userId: String): List<Sickness>

    @PUT("api/v1/sickness/affected/change/{sicknessId}/")
    suspend fun updateAffectedSickness(@Path("sicknessId") sicknessId: String): Boolean

    @GET("api/v1/sickness/affected/status/{sicknessId}/")
    suspend fun statusAffectedSickness(@Path("sicknessId") sicknessId: String): Boolean

}


object SicknessServiceImp: BaseService<SicknessService>(SicknessService::class.java)
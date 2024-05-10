package com.fyp.app.data.api

import com.fyp.app.data.model.db.Sickness
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SicknessService {

    @GET("sickness/list")
    suspend fun getSicknesses(): List<Sickness>

    @POST("sickness/create")
    suspend fun addSickness(@Body sickness: Sickness): Sickness

    @GET("sickness/{sicknessId}")
    suspend fun getSicknessById(@Path("sicknessId") sicknessId: Int): Sickness

    @POST("sicknesses/{sicknessId}")
    suspend fun updateSickness(@Path("sicknessId") sicknessId: Int, @Body sickness: Sickness): Sickness
}

object SicknessServiceImp {
    private var instance: SicknessService? = null

    fun getInstance(): SicknessService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SicknessService::class.java)
        }
        return instance!!
    }

    private const val BASE_URL = "https://example.com/api/" // Reemplaza esto con tu URL base
}
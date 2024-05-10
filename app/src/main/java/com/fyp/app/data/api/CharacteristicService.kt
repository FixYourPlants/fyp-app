package com.fyp.app.data.api

import com.fyp.app.data.model.db.Characteristic
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CharacteristicService {

    @GET("characteristics/list")
    suspend fun getCharacteristics(): List<Characteristic>

    @POST("characteristics/create")
    suspend fun addCharacteristic(@Body characteristic: Characteristic): Characteristic

    @GET("characteristic/{characteristicId}")
    suspend fun getCharacteristicById(@Path("characteristicId") characteristicId: Int): Characteristic

    @PUT("characteristics/{characteristicId}")
    suspend fun updateCharacteristic(@Path("characteristicId") characteristicId: Int, @Body characteristic: Characteristic): Characteristic

    @DELETE("characteristics/{characteristicId}")
    suspend fun deleteCharacteristic(@Path("characteristicId") characteristicId: Int): Characteristic
}

object CharacteristicServiceImp {
    private var instance: CharacteristicService? = null

    fun getInstance(): CharacteristicService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CharacteristicService::class.java)
        }
        return instance!!
    }

    private const val BASE_URL = "https://example.com/api/" // Reemplaza esto con tu URL base
}
package com.fyp.app.data.api

import com.fyp.app.data.model.db.Plant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlantService {
    @GET("plants/list")
    suspend fun getPlants():ArrayList<Plant>

    @POST("plants/create")
    suspend fun addPlant(@Body plant: Plant): Plant

    @GET("plant/{plantId}")
    suspend fun getPlantById(@Path("plantId") plantId:Int): Plant

    @POST("plants/{plantId}")
    suspend fun updatePlant(@Path("plantId") plantId:Int, @Body plant: Plant): Plant

    @DELETE("plants/{plantId}")
    suspend fun deletePlant(@Path("plantId") plantId:Int): Plant
    @GET("plants/fav")
    suspend fun getFavPlants(@Path("userId") userId:Int):ArrayList<Plant>

    @POST("plants/fav/add")
    suspend fun addFavPlant(@Body plant: Plant): Plant

    @POST("plants/fav/remove")
    suspend fun removeFavPlant(@Body plant: Plant): Plant
}


object PlantServiceImp {
    private var instance: PlantService? = null

    fun getInstance(): PlantService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlantService::class.java)
        }
        return instance!!
    }

    private const val BASE_URL = "https://example.com/api/" // Reemplaza esto con tu URL base
}
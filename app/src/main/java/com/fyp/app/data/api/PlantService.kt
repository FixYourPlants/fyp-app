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
    @GET("api/v1/plants/list/")
    suspend fun getPlants():ArrayList<Plant>

    @POST("api/v1/plants/create")
    suspend fun addPlant(@Body plant: Plant): Plant

    @GET("api/v1/plant/{id}/")
    suspend fun getPlantById(@Path("id") plantId:Int): Plant

    @POST("api/v1/plants/{id}")
    suspend fun updatePlant(@Path("id") plantId:Int, @Body plant: Plant): Plant

    @DELETE("api/v1/plants/{id}")
    suspend fun deletePlant(@Path("id") plantId:Int): Plant
    @GET("api/v1/plants/fav")
    suspend fun getFavPlants(@Path("userId") userId:Int):ArrayList<Plant>

    @POST("api/v1/plants/fav/add")
    suspend fun addFavPlant(@Body plant: Plant): Plant

    @POST("api/v1/plants/fav/remove")
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

    private const val BASE_URL = "http://10.0.2.2:8000/" // Reemplaza esto con tu URL base
}
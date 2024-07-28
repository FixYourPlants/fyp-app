package com.fyp.app.data.api

import com.fyp.app.data.model.db.History
import com.fyp.app.data.model.db.Plant
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface PlantService {
    @GET("api/v1/plants/list/")
    suspend fun getPlants(): ArrayList<Plant>

    @GET("api/v1/plant/{id}/")
    suspend fun getPlantById(@Path("id") plantId: String): Plant

    @PUT("api/v1/plant/fav/change/{id}/")
    suspend fun toggleFavPlant(@Path("id") plantId: String): Boolean

    @GET("api/v1/plant/fav/status/{id}/")
    suspend fun statusFavPlant(@Path("id") plantId: String): Boolean

    @Multipart
    @POST("api/v1/plants/predict/")
    suspend fun predictPlant(@Part image: MultipartBody.Part): History
}

object PlantServiceImp: BaseService<PlantService>(PlantService::class.java)
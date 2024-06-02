package com.fyp.app.data.api

import android.util.Log
import com.fyp.app.BuildConfig
import com.fyp.app.data.model.db.Plant
import com.fyp.app.utils.UserPreferencesImp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PlantService {
    @GET("api/v1/plants/list/")
    suspend fun getPlants(): ArrayList<Plant>

    @POST("api/v1/plants/create")
    suspend fun addPlant(@Body plant: Plant): Plant

    @GET("api/v1/plant/{id}/")
    suspend fun getPlantById(@Path("id") plantId: String): Plant

    @POST("api/v1/plants/{id}/")
    suspend fun updatePlant(@Path("id") plantId: String, @Body plant: Plant): Plant

    @DELETE("api/v1/plants/{id}/")
    suspend fun deletePlant(@Path("id") plantId: String): Plant

    @GET("api/v1/plants/fav/")
    suspend fun getFavPlants(@Path("userId") userId: String): ArrayList<Plant>

    @PUT("api/v1/plant/fav/change/{id}/")
    suspend fun toggleFavPlant(@Path("id") plantId: String): Boolean

    @GET("api/v1/plant/fav/status/{id}/")
    suspend fun statusFavPlant(@Path("id") plantId: String): Boolean
}


object PlantServiceImp {
    private var instance: PlantService? = null
    private var addedToken = false

    fun getInstance(): PlantService {
        try {
            val token = UserPreferencesImp.getInstance().access
            Log.d("token", token)
            val httpClient = OkHttpClient.Builder()
            if (!addedToken) {
                httpClient.addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer $token")
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                instance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BACKEND_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PlantService::class.java)
                addedToken = true
            }
        } catch (e: Exception) {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BACKEND_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PlantService::class.java)
            }
        }
        return instance!!
    }
}
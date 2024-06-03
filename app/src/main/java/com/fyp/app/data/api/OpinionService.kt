package com.fyp.app.data.api

import android.util.Log
import com.fyp.app.BuildConfig
import com.fyp.app.data.model.db.CreateOpinion
import com.fyp.app.data.model.db.Opinion
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
import retrofit2.http.Query

interface OpinionService {

    @GET("api/v1/opinions/lists/")
    suspend fun getOpinions(@Query("plant_id") plantId: String? = null): List<Opinion>

    @POST("api/v1/opinions/create/")
    suspend fun addOpinion(@Body opinion: CreateOpinion): CreateOpinion

    @GET("opinion/{opinionId}")
    suspend fun getOpinionById(@Path("opinionId") opinionId: Int): CreateOpinion

    @PUT("opinions/{opinionId}")
    suspend fun updateOpinion(@Path("opinionId") opinionId: Int, @Body opinion: CreateOpinion): CreateOpinion

    @DELETE("opinions/{opinionId}")
    suspend fun deleteOpinion(@Path("opinionId") opinionId: Int): CreateOpinion
}

object OpinionServiceImp {
    private var instance: OpinionService? = null
    private var addedToken = false

    fun getInstance(): OpinionService {
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
                    .create(OpinionService::class.java)
                addedToken = true
            }
        } catch (e: Exception) {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BACKEND_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OpinionService::class.java)
            }
        }

        return instance!!
    }
}

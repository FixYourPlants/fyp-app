package com.fyp.app.data.api

import android.util.Log
import com.fyp.app.BuildConfig
import com.fyp.app.data.model.db.Alert
import com.fyp.app.utils.UserPreferencesImp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AlertService {
    @GET("api/v1/alerts/list/")
    suspend fun getAlerts(): ArrayList<Map<String,String>>

    @GET("api/v1/alerts/list/gob/")
    suspend fun getAlertsGob(): ArrayList<Alert>

    @GET("api/v1/alerts/details/")
    suspend fun getAlertDetails(@Query("link") link: String): Map<String,String>
}


object AlertServiceImp {
    private var instance: AlertService? = null
    private var addedToken = false

    fun getInstance(): AlertService {
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
                    .create(AlertService::class.java)
                addedToken = true
            }
        } catch (e: Exception) {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BACKEND_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AlertService::class.java)
            }
        }
        return instance!!
    }
}
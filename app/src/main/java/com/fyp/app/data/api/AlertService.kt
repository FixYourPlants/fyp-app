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

@RefreshableService
object AlertServiceImp : BaseService<AlertService>(AlertService::class.java)
package com.fyp.app.data.api

import android.util.Log
import com.fyp.app.BuildConfig
import com.fyp.app.utils.UserPreferencesImp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseService<T>(private val serviceClass: Class<T>) {
    @Volatile
    private var instance: T? = null
    private var addedToken = false

    fun getInstance(): T {
        return instance ?: synchronized(this) {
            if (!addedToken) {
                Log.d("TOKEN ADDING", "Adding token in getInstance")
                instance = createInstance()
            }
            instance ?: createInstance().also { instance = it }
        }
    }

    private fun createInstance(): T {
        return try {
            val token = UserPreferencesImp.getInstance().access

            if (token.isEmpty()) {
                throw IllegalArgumentException("Token is missing or invalid")
            }

            Log.d("TOKEN ADDING", "Creating HTTP client with token: $token")

            val httpClient = OkHttpClient.Builder()
            if (!addedToken) {
                httpClient.addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer $token")
                    val request = requestBuilder.build()
                    Log.d("REQUEST", request.toString())
                    chain.proceed(request)
                }
                addedToken = true
            }

            Retrofit.Builder()
                .baseUrl(BuildConfig.BACKEND_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceClass)
        } catch (e: Exception) {
            Log.e("ERROR TOKEN", e.stackTraceToString())
            Retrofit.Builder()
                .baseUrl(BuildConfig.BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceClass)
        }
    }

    fun resetToken() {
        addedToken = false
        instance = null
    }
}
package com.fyp.app.data.api

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
            instance ?: createInstance().also { instance = it }
        }
    }

    private fun createInstance(): T {
        return try {
            val token = UserPreferencesImp.getInstance().access
            val httpClient = OkHttpClient.Builder()
            if (!addedToken) {
                httpClient.addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer $token")
                    val request = requestBuilder.build()
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
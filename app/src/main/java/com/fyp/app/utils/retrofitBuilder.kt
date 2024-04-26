package com.fyp.app.utils

import com.fyp.app.data.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofitService(): ApiService {
    val urlBase="http://10.0.2.2:8000/"
    var retrofit = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: ApiService = retrofit.create(ApiService::class.java)
    return service
}

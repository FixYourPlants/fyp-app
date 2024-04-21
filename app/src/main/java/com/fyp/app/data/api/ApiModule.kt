package com.fyp.app.data.api

import com.fyp.app.utils.createRetrofitService

object ApiModule {
    val apiService: ApiService by lazy { createRetrofitService() }
}
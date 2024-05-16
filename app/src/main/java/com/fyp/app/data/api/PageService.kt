package com.fyp.app.data.api

import com.fyp.app.data.model.db.Page
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PageService {

    @GET("pages/list")
    suspend fun getPages():ArrayList<Page>

    @POST("pages/create")
    suspend fun addPage(@Body page: Page): Page

    @GET("page/{pageId}")
    suspend fun getPageById(@Path("pageId") pageId:Int): Page

    @PUT("pages/{pageId}")
    suspend fun updatePage(@Path("pageId") pageId:Int, @Body page: Page): Page

    @DELETE("pages/{pageId}")
    suspend fun deletePage(@Path("pageId") pageId:Int): Page
}

object PageServiceSingleton {
    private var instance: PageService? = null

    fun getInstance(): PageService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PageService::class.java)
        }
        return instance!!
    }

    private const val BASE_URL = "http://10.0.2.2:8000/" // Reemplaza esto con tu URL base
}
package com.fyp.app.data.api

import com.fyp.app.BuildConfig
import com.fyp.app.data.model.db.Page
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface PageService {

    @GET("api/v1/pages/list/")
    suspend fun getPages(@Query("diary_id") diaryId: String): List<Page>

    @Multipart
    @POST("api/v1/pages/create/")
    suspend fun addPage(
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody,
        @Part image: MultipartBody.Part?,
        @Part("diary") diary: RequestBody
    ): Page

    @GET("page/{pageId}")
    suspend fun getPageById(@Path("pageId") pageId:Int): Page

    @PUT("api/v1/pages/{pageId}/")
    suspend fun updatePage(@Path("pageId") pageId:String, @Body page: Page): Page

    @DELETE("pages/{pageId}")
    suspend fun deletePage(@Path("pageId") pageId:Int): Page
}

object PageServiceImp {
    private var instance: PageService? = null

    fun getInstance(): PageService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BuildConfig.BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PageService::class.java)
        }
        return instance!!
    }
}

package com.fyp.app.data.api

import com.fyp.app.data.model.db.Page
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
    ): ResponseBody
}

object PageServiceImp: BaseService<PageService>(PageService::class.java)

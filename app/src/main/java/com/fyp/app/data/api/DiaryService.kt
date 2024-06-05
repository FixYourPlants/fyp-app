package com.fyp.app.data.api

import android.util.Log
import com.fyp.app.BuildConfig
import com.fyp.app.data.model.db.Diary
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

interface DiaryService {

    @GET("api/v1/diaries/list/")
    suspend fun getDiaries(): List<Diary>

    @POST("diaries/create")
    suspend fun addDiary(@Body diary: Diary): Diary

    @GET("diary/{diaryId}")
    suspend fun getDiaryById(@Path("diaryId") diaryId: Int): Diary

    @PUT("diaries/{diaryId}")
    suspend fun updateDiary(@Path("diaryId") diaryId: Int, @Body diary: Diary): Diary

    @DELETE("diaries/{diaryId}")
    suspend fun deleteDiary(@Path("diaryId") diaryId: Int): Diary
}

object DiaryServiceImp {
    private var instance: DiaryService? = null
    private var addedToken = false

    fun getInstance(): DiaryService {
        try {
            val token = UserPreferencesImp.getInstance().access
            Log.d("tokenDiary", token)
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
                    .create(DiaryService::class.java)
                addedToken = true
            }
        } catch (e: Exception) {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BACKEND_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DiaryService::class.java)
            }
        }
        return instance!!
    }
}
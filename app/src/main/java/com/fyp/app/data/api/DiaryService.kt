package com.fyp.app.data.api

import com.fyp.app.data.model.db.Diary
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


object DiaryServiceImp: BaseService<DiaryService>(DiaryService::class.java)
package com.fyp.app.data.api

import com.fyp.app.data.model.db.Diary
import retrofit2.http.GET

interface DiaryService {

    @GET("api/v1/diaries/list/")
    suspend fun getDiaries(): List<Diary>
}

object DiaryServiceImp: BaseService<DiaryService>(DiaryService::class.java)
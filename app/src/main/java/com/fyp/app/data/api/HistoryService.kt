package com.fyp.app.data.api

import com.fyp.app.data.model.db.History
import retrofit2.http.GET

interface HistoryService {
    @GET("/api/v1/histories/lists/")
    suspend fun getHistories(): List<History>
}


object HistoryServiceImp: BaseService<HistoryService>(HistoryService::class.java)
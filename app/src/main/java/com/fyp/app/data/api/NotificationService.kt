package com.fyp.app.data.api

import com.fyp.app.data.model.db.Notification
import retrofit2.http.GET
import retrofit2.http.Query

interface NotificationService {

    @GET("/api/v1/notifications/list/")
    suspend fun getNotifications(@Query("start_datetime") startDatetime: String, @Query("end_datetime") endDatetime: String): List<Notification>
}


object NotificationServiceImp: BaseService<NotificationService>(NotificationService::class.java)
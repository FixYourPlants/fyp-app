package com.fyp.app.data.api

import com.fyp.app.data.model.db.Notification
import com.fyp.app.data.model.db.Sickness
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationService {

    @GET("/api/v1/notifications/list/")
    suspend fun getNotifications(@Query("start_datetime") startDatetime: String, @Query("end_datetime") endDatetime: String): List<Notification>
}


object NotificationServiceImp: BaseService<NotificationService>(NotificationService::class.java)
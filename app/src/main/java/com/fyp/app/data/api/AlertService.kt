package com.fyp.app.data.api

import com.fyp.app.data.model.db.Alert
import retrofit2.http.GET
import retrofit2.http.Query

interface AlertService {
    @GET("api/v1/alerts/list/gob/")
    suspend fun getAlertsGob(): ArrayList<Alert>
}

object AlertServiceImp : BaseService<AlertService>(AlertService::class.java)
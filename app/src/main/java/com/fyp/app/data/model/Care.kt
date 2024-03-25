package com.fyp.app.data.model

data class Care(
    val type: CareType,
    val actions: String,
    val illness: Illness?=null,
    val plant: Plant?=null
)
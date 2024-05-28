package com.fyp.app.data.model.db

import java.io.Serializable

data class Diary(
    // TODO
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val userId: Int
): Serializable

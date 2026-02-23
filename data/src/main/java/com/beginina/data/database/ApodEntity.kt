package com.beginina.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apods")
data class ApodEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val url: String,
    val mediaType: String,
    val explanation: String,
    val date: String
)

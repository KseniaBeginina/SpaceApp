package com.beginina.data.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beginina.data.database.ApodDao
import com.beginina.data.database.ApodEntity

@Database(entities = [ApodEntity::class], version = 1)
abstract class ApodDatabase(): RoomDatabase() {
    abstract fun apodDao(): ApodDao
}
package com.beginina.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ApodDao {
    @Insert
    suspend fun insertAll(apods: List<ApodEntity>)

    @Query("DELETE FROM apods")
    suspend fun deleteAll()

    @Query("SELECT * FROM apods WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    suspend fun getApodsPaged(startDate: String, endDate: String): List<ApodEntity>

    @Query("SELECT * FROM apods WHERE id = :id")
    suspend fun getApodById(id: Int): ApodEntity?
}
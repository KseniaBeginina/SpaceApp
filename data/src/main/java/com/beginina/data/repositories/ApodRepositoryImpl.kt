package com.beginina.data.repositories

import android.util.Log
import com.beginina.data.api.ApodService
import com.beginina.data.database.ApodDao
import com.beginina.data.mapper.toDomain
import com.beginina.data.mapper.toEntity
import com.beginina.data.mapper.toModel
import com.beginina.domain.models.ApodModel
import com.beginina.domain.repositories.ApodRepository
import java.time.LocalDate

class ApodRepositoryImpl(
    private val apodService: ApodService,
    private val apodDao: ApodDao
): ApodRepository {
    override suspend fun getApodList(
            startDate: LocalDate,
            endDate: LocalDate
        ): Pair<List<ApodModel>, Boolean> {
        val apods = try {
            apodService
                .getApodRange(startDate = startDate.toString(), endDate = endDate.toString())
                .map { it.toDomain() }
        } catch (e: Exception){
            Log.d("getApodList", "Error loading apods", e)
            emptyList()
        }

        if (apods.isNotEmpty()){
            apodDao.deleteAll()
            apodDao.insertAll(apods.map { it.toEntity() })
            return Pair(apods, false)
        }

        val cashed = apodDao
            .getApodsPaged(startDate = startDate.toString(), endDate = endDate.toString())
            .map { it.toModel() }
        return Pair(cashed, true)
    }

    override suspend fun getApodById(id: Int): ApodModel? {
        return apodDao.getApodById(id)?.toModel()
    }
}
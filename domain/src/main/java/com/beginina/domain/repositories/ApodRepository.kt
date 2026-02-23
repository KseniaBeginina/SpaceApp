package com.beginina.domain.repositories

import com.beginina.domain.models.ApodModel
import java.time.LocalDate

interface ApodRepository {
    suspend fun getApodList(
        startDate: LocalDate,
        endDate: LocalDate
    ): Pair<List<ApodModel>, Boolean>

    suspend fun getApodById(id: Int): ApodModel?
}
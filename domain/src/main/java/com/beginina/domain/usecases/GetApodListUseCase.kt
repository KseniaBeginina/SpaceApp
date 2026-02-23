package com.beginina.domain.usecases

import com.beginina.domain.repositories.ApodRepository
import java.time.LocalDate

class GetApodListUseCase(
    private val apodRepository: ApodRepository
){
    suspend operator fun invoke(startDate: LocalDate, endDate: LocalDate) = apodRepository.getApodList(startDate = startDate, endDate = endDate)
}
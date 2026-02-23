package com.beginina.domain.usecases

import com.beginina.domain.repositories.ApodRepository

class GetApodByIdUseCase(
    private val apodRepository: ApodRepository
){
    suspend operator fun invoke(id: Int) = apodRepository.getApodById(id)
}
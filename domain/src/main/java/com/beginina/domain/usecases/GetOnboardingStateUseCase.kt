package com.beginina.domain.usecases

import com.beginina.domain.repositories.OnboardingRepository

class GetOnboardingStateUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke() = onboardingRepository.isOnboardingShown()
}
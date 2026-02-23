package com.beginina.domain.usecases

import com.beginina.domain.repositories.OnboardingRepository

class SetOnboardingStateUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke() = onboardingRepository.setOnboardingShown()
}
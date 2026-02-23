package com.beginina.data.repositories

import com.beginina.data.storages.PreferencesStorage
import com.beginina.domain.repositories.OnboardingRepository

class OnboardingRepositoryImpl(
    private val preferencesStorage: PreferencesStorage
): OnboardingRepository {
    override suspend fun isOnboardingShown() = preferencesStorage.isOnboardingShown()

    override suspend fun setOnboardingShown() = preferencesStorage.setOnboardingShown()
}
package com.beginina.domain.repositories

interface OnboardingRepository {
    suspend fun isOnboardingShown(): Boolean
    suspend fun setOnboardingShown()
}
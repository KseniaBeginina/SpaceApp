package com.beginina.spaceapp.di

import com.beginina.data.repositories.ApodRepositoryImpl
import com.beginina.data.repositories.OnboardingRepositoryImpl
import com.beginina.domain.usecases.GetApodByIdUseCase
import com.beginina.domain.usecases.GetApodListUseCase
import com.beginina.domain.usecases.GetOnboardingStateUseCase
import com.beginina.domain.usecases.SetOnboardingStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetApodListUseCase(apodRepository: ApodRepositoryImpl) = GetApodListUseCase(apodRepository)

    @Provides
    fun provideGetApodByIdUseCase(apodRepository: ApodRepositoryImpl) = GetApodByIdUseCase(apodRepository)

    @Provides
    fun provideGetOnboardingStateUseCase(onboardingRepository: OnboardingRepositoryImpl) =
        GetOnboardingStateUseCase(onboardingRepository)

    @Provides
    fun provideSetOnboardingStateUseCase(onboardingRepository: OnboardingRepositoryImpl) =
        SetOnboardingStateUseCase(onboardingRepository)

}
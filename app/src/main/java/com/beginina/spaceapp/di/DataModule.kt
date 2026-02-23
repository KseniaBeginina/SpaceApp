package com.beginina.spaceapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.beginina.data.api.ApodDatabase
import com.beginina.data.api.ApodService
import com.beginina.data.database.ApodDao
import com.beginina.data.repositories.ApodRepositoryImpl
import com.beginina.data.repositories.OnboardingRepositoryImpl
import com.beginina.data.storages.PreferencesStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    
    @Provides
    @Singleton
    fun provideApodService(): ApodService{

        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApodService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context) = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providePreferencesStorage(sharedPreferences: SharedPreferences) = PreferencesStorage(sharedPreferences)

    @Provides
    @Singleton
    fun provideApodDatabase(app: Application) = Room.databaseBuilder(app, ApodDatabase::class.java, "apod_db").build()

    @Provides
    @Singleton
    fun provideApodDao(db: ApodDatabase) = db.apodDao()

    @Provides
    @Singleton
    fun provideApodRepository(
        apodService: ApodService,
        apodDao: ApodDao
    ) = ApodRepositoryImpl(apodService = apodService, apodDao = apodDao)

    @Provides
    @Singleton
    fun provideOnboardingRepository(preferencesStorage: PreferencesStorage) = OnboardingRepositoryImpl(preferencesStorage)


}
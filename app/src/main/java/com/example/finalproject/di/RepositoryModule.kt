package com.example.finalproject.di

import com.example.finalproject.data.common.response_handler.HandleResponse
import com.example.finalproject.data.repository.WallpaperRepositoryImpl
import com.example.finalproject.data.service.WallpaperApiService
import com.example.finalproject.domain.repository.WallpaperRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWallpaperRepository(wallpaperApiService: WallpaperApiService): WallpaperRepository =
        WallpaperRepositoryImpl(apiService = wallpaperApiService)
}
package com.hizari.data.di

import com.hizari.data.network.service.NewsService
import com.hizari.data.repository.NewsRepository
import com.hizari.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(newsService: NewsService): NewsRepository {
        return NewsRepositoryImpl(newsService)
    }

}
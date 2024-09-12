package com.hizari.domain.di

import com.hizari.data.repository.NewsRepository
import com.hizari.domain.usecase.GetNewsBasedOnLatestUseCase
import com.hizari.domain.usecase.GetNewsBasedOnPopularityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object NewsModule {

    @Provides
    fun provideGetNewsBasedOnPopularityUseCase(newsRepository: NewsRepository): GetNewsBasedOnPopularityUseCase {
        return GetNewsBasedOnPopularityUseCase(newsRepository)
    }

    @Provides
    fun provideGetNewsBasedOnLatestUseCase(newsRepository: NewsRepository): GetNewsBasedOnLatestUseCase {
        return GetNewsBasedOnLatestUseCase(newsRepository)
    }

}
package com.hizari.domain.di

import android.content.Context
import com.hizari.data.repository.NewsRepository
import com.hizari.domain.usecase.GetNewsBasedOnLatestUseCase
import com.hizari.domain.usecase.GetNewsBasedOnPopularityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object NewsModule {

    @Provides
    fun provideGetNewsBasedOnPopularityUseCase(
        @ApplicationContext context: Context,
        newsRepository: NewsRepository
    ): GetNewsBasedOnPopularityUseCase {
        return GetNewsBasedOnPopularityUseCase(context, newsRepository)
    }

    @Provides
    fun provideGetNewsBasedOnLatestUseCase(
        @ApplicationContext context: Context,
        newsRepository: NewsRepository
    ): GetNewsBasedOnLatestUseCase {
        return GetNewsBasedOnLatestUseCase(context, newsRepository)
    }

}
package com.hizari.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.hizari.data.network.service.NewsService
import com.hizari.data.network.util.Client
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext
        context: Context
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

    @Provides
    @Singleton
    fun provideClient(
        chuckerInterceptor: ChuckerInterceptor
    ): Client {
        return Client(chuckerInterceptor)
    }

    @Provides
    @Singleton
    fun provideNewsService(client: Client): NewsService{
        return NewsService.invoke(client)
    }

}
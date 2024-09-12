package com.hizari.domain.usecase

import android.content.Context
import com.hizari.common.util.Resources
import com.hizari.data.repository.NewsRepository
import com.hizari.domain.model.News
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNewsBasedOnLatestUseCase(
    @ApplicationContext private val context: Context,
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<Resources<List<News>?>> = flow {
        emit(Resources.Loading())
        try {
            val response = newsRepository.getNewsList()
            val sortedResponse = response.sortedBy { it.timeCreated }
            val mappedResponse = sortedResponse.map { News.from(context, it) }

            emit(Resources.Success(mappedResponse))
        } catch (e: Exception) {
            emit(Resources.Error(e))
        }
    }

}
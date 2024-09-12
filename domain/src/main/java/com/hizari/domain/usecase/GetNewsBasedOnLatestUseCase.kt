package com.hizari.domain.usecase

import android.content.Context
import com.hizari.common.util.Resources
import com.hizari.data.repository.NewsRepository
import com.hizari.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNewsBasedOnLatestUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(context: Context): Flow<Resources<List<News>?>> = flow {
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
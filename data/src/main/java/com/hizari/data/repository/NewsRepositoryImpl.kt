package com.hizari.data.repository

import com.hizari.data.network.service.NewsService
import com.hizari.data.network.util.SafeApiRequest
import com.hizari.domain.model.News
import com.hizari.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
): NewsRepository, SafeApiRequest() {

    override suspend fun getNewsList(): List<News> {
        val response = apiRequest { newsService.getNewsList() }
        return response?.map { it.toDomain(context) }.orEmpty()
    }

}
package com.hizari.data.repository

import com.hizari.data.network.model.dto.NewsDTO
import com.hizari.data.network.service.NewsService
import com.hizari.data.network.util.SafeApiRequest
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
): NewsRepository, SafeApiRequest() {

    override suspend fun getNewsList(): List<NewsDTO> {
        val response = apiRequest { newsService.getNewsList() }
        return response.orEmpty()
    }

}
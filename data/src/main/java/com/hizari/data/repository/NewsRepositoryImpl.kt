package com.hizari.data.repository

import com.hizari.data.network.model.dto.NewsListDTO
import com.hizari.data.network.service.NewsService
import com.hizari.data.network.util.SafeApiRequest
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
): NewsRepository, SafeApiRequest() {

    override suspend fun getNewsList(): NewsListDTO {
        val response = apiRequest { newsService.getNewsList() }
        return response ?: NewsListDTO()
    }

}
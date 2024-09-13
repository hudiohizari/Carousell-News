package com.hizari.data.repository

import com.hizari.data.network.model.dto.NewsDTO

interface NewsRepository {
    suspend fun getNewsList(): List<NewsDTO>
}
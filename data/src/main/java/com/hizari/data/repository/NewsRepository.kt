package com.hizari.data.repository

import com.hizari.data.network.model.dto.NewsListDTO

interface NewsRepository {
    suspend fun getNewsList(): NewsListDTO
}
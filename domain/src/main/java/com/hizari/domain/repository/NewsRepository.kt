package com.hizari.domain.repository

import com.hizari.domain.model.News


interface NewsRepository {
    suspend fun getNewsList(): List<News>
}
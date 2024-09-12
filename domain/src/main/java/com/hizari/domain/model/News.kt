package com.hizari.domain.model

data class News(
    val id: String,
    val title: String,
    val description: String,
    val bannerUrl: String,
    val timeAgo: String
)

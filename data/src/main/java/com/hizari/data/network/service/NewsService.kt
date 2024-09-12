package com.hizari.data.network.service

import com.hizari.common.util.Constant
import com.hizari.data.network.model.dto.NewsListDTO
import com.hizari.data.network.util.Client
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {

    @GET("carousell_news.json")
    suspend fun getNewsList(): Response<NewsListDTO>

    companion object {
        operator fun invoke(client: Client): NewsService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.URL.NEWS_BASE_URL)
                .client(client.provideClient())
                .build()
                .create(NewsService::class.java)
        }
    }

}
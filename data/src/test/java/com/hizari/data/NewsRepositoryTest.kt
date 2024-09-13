package com.hizari.data

import android.content.Context
import com.hizari.data.network.model.dto.NewsDTO
import com.hizari.data.network.service.NewsService
import com.hizari.data.network.util.ApiException
import com.hizari.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class NewsRepositoryTest {

    @Mock
    private lateinit var mockNewsService: NewsService

    @Mock
    private lateinit var mockContext: Context

    @InjectMocks
    private lateinit var newsRepository: NewsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        newsRepository.context = mockContext
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `getNewsList should return NewsListDTO when API request is successful`() = runTest {
        val mockResponse = mock(List::class.java) as List<NewsDTO>
        `when`(mockNewsService.getNewsList()).thenReturn(Response.success(mockResponse))

        val result = newsRepository.getNewsList()

        assertEquals(mockResponse, result)
    }

    @Test
    fun `getNewsList should return empty NewsListDTO when API response is null`() = runTest {
        val newsMock = listOf<NewsDTO>()
        `when`(mockNewsService.getNewsList()).thenReturn(Response.success(newsMock))

        val result = newsRepository.getNewsList()

        assertEquals(newsMock, result)
    }

    @Test
    fun `getNewsList should throw ApiException when API request fails 500`() = runTest {
        val errorJson = "500"
        val errorResponse = Response.error<List<NewsDTO>>(500, errorJson.toResponseBody())

        `when`(mockContext.getString(R.string.server_down)).thenReturn("Server down")
        `when`(mockNewsService.getNewsList()).thenReturn(errorResponse)

        val exception = assertThrows(ApiException::class.java) {
            runBlocking {
                newsRepository.getNewsList()
            }
        }
        assertEquals("500", exception.code)
        assertEquals("Server down", exception.message)
    }
}

package com.hizari.domain

import android.content.Context
import app.cash.turbine.test
import com.hizari.common.util.Resources
import com.hizari.data.network.model.dto.NewsDTO
import com.hizari.data.repository.NewsRepository
import com.hizari.domain.model.News
import com.hizari.domain.usecase.GetNewsBasedOnRecentUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertIs
import android.content.res.Resources as AndroidResources

class GetNewsBasedOnRecentUseCaseTest {

    private lateinit var getNewsBasedOnRecentUseCase: GetNewsBasedOnRecentUseCase

    @Mock
    private lateinit var newsRepository: NewsRepository

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockAndroidResources: AndroidResources

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getNewsBasedOnRecentUseCase = GetNewsBasedOnRecentUseCase(mockContext, newsRepository)
    }

    @Test
    fun `should emit Loading and Success states when repository returns data sorted by timeCreated`() =
        runTest {
            val newsDtoList = listOf(
                NewsDTO("Title 1", "Content 1", "Desc 1", "", 2, 3),
                NewsDTO("Title 2", "Content 2", "Desc 2", "", 3, 1),
                NewsDTO("Title 3", "Content 3", "Desc 3", "", 1, 2)
            )

            `when`(mockAndroidResources.getQuantityString(anyInt(), anyInt(), any())).thenReturn("Just now")
            `when`(mockContext.resources).thenReturn(mockAndroidResources)
            `when`(newsRepository.getNewsList()).thenReturn(newsDtoList)

            getNewsBasedOnRecentUseCase().test {
                assertIs<Resources.Loading<List<News>>>(awaitItem())

                val sortedMappedNews = newsDtoList
                    .sortedByDescending { it.timeCreated }
                    .map { News.from(mockContext, it) }
                awaitItem().let {
                    assertIs<Resources.Success<List<News>>>(it)
                    assertEquals(it.data, sortedMappedNews)
                }

                awaitComplete()
            }
        }

    @Test
    fun `invoke should emit Error state when repository throws an exception`() = runTest {
        val exception = RuntimeException("Network error")
        `when`(newsRepository.getNewsList()).thenThrow(exception)


        getNewsBasedOnRecentUseCase().test {
            assertIs<Resources.Loading<List<News>>>(awaitItem())

            awaitItem().let {
                assertIs<Resources.Error<List<News>>>(it)
                assertEquals(it.throwable?.message, "Network error")
            }

            awaitComplete()
        }
    }
}
package com.hizari.carouselltest

import com.hizari.common.util.Resources
import com.hizari.domain.model.News
import com.hizari.domain.usecase.GetNewsBasedOnLatestUseCase
import com.hizari.domain.usecase.GetNewsBasedOnPopularityUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var getNewsBasedOnLatestUseCase: GetNewsBasedOnLatestUseCase
    @Mock
    private lateinit var getNewsBasedOnPopularityUseCase: GetNewsBasedOnPopularityUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(getNewsBasedOnLatestUseCase, getNewsBasedOnPopularityUseCase)
    }

    @Test
    fun `loadNews Latest should update viewState with latest news`() = mainCoroutineRule.testScope.runTest {
        val latestNews =  listOf(
            News("Title 3", "Content 3", "Desc 3", "", "1 ago"),
            News("Title 2", "Content 2", "Desc 2", "", "3 ago"),
            News("Title 1", "Content 1", "Desc 1", "", "2 ago")
        )
        val expectedResult = Resources.Success(latestNews)
        `when`(getNewsBasedOnLatestUseCase()).thenReturn(flow { emit(expectedResult) })

        viewModel.loadNews(MainViewState.NewsType.Latest)

        println("Expected result: $expectedResult")
        println("Actual result: ${viewModel.viewState.value.newsResource}")

        assertEquals(expectedResult, viewModel.viewState.value.newsResource)
        assertEquals(null, viewModel.viewState.value.loadNewsType)
    }

    @Test
    fun `loadNews Popular should update viewState with popular news`() = mainCoroutineRule.testScope.runTest {
        val popularNews = listOf(
            News("Title 2", "Content 2", "Desc 2", "", "3 ago"),
            News("Title 3", "Content 3", "Desc 3", "", "1 ago"),
            News("Title 1", "Content 1", "Desc 1", "", "2 ago")
        )
        val expectedResult = Resources.Success(popularNews)
        `when`(getNewsBasedOnPopularityUseCase()).thenReturn(flow { emit(expectedResult) })

        viewModel.loadNews(MainViewState.NewsType.Popular)

        println("Expected result: $expectedResult")
        println("Actual result: ${viewModel.viewState.value.newsResource}")


        assertEquals(expectedResult, viewModel.viewState.value.newsResource)
        assertEquals(null, viewModel.viewState.value.loadNewsType)
    }

    @Test
    fun `loadNews Latest should handle error`() = mainCoroutineRule.testScope.runTest {
        val errorResult = Resources.Error<List<News>>(Throwable("Failed to load news"))
        `when`(getNewsBasedOnLatestUseCase()).thenReturn(flow { emit(errorResult) })

        viewModel.loadNews(MainViewState.NewsType.Latest)

        assertEquals(errorResult, viewModel.viewState.value.newsResource)
        assertEquals(null, viewModel.viewState.value.loadNewsType)
    }

    @Test
    fun `loadNews Popular should handle error`() = mainCoroutineRule.testScope.runTest {
        val errorResult = Resources.Error<List<News>>(Throwable("Failed to load news"))
        `when`(getNewsBasedOnPopularityUseCase()).thenReturn(flow { emit(errorResult) })

        viewModel.loadNews(MainViewState.NewsType.Popular)

        assertEquals(errorResult, viewModel.viewState.value.newsResource)
        assertEquals(null, viewModel.viewState.value.loadNewsType)
    }
}

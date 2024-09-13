package com.hizari.carouselltest

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hizari.domain.usecase.GetNewsBasedOnRecentUseCase
import com.hizari.domain.usecase.GetNewsBasedOnPopularityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsBasedOnRecentUseCase: GetNewsBasedOnRecentUseCase,
    private val getNewsBasedOnPopularityUseCase: GetNewsBasedOnPopularityUseCase,
): ViewModel() {

    private val mutableViewState = mutableStateOf(MainViewState())
    val viewState: MutableState<MainViewState> = mutableViewState

    fun loadNews(newsType: MainViewState.NewsType?) {
        when (newsType) {
            null -> return
            MainViewState.NewsType.Recent -> getNewsBasedOnRecentUseCase()
            MainViewState.NewsType.Popular -> getNewsBasedOnPopularityUseCase()
        }.onEach { res ->
            mutableViewState.value = viewState.value.copy(newsResource = res)
        }.launchIn(viewModelScope)

        mutableViewState.value = viewState.value.copy(
            loadNewsType = null,
            lastLoadNewsType = newsType
        )
    }

}
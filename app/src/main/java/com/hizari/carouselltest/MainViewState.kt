package com.hizari.carouselltest

import com.hizari.common.util.Resources
import com.hizari.domain.model.News

data class MainViewState(
    val isRefreshing: Boolean? = null,
    val newsResource: Resources<List<News>> = Resources.Empty(),
    val loadNewsType: NewsType? = null,
    val lastLoadNewsType: NewsType? = null,
) {

    enum class NewsType {
        Recent, Popular,
    }

}

package com.hizari.carouselltest.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hizari.carouselltest.ui.component.extention.bottomFadingEdge
import com.hizari.carouselltest.ui.component.extention.topFadingEdge
import com.hizari.domain.model.News

@Composable
fun NewsListComposable(
    modifier: Modifier = Modifier,
    items: List<News>,
    onItemClick: (News) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .bottomFadingEdge(isVisible = listState.canScrollForward)
            .topFadingEdge(isVisible = listState.canScrollBackward)
            .padding(horizontal = 16.dp),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }

        itemsIndexed(items) { _, item ->
            NewsItemComposable(
                modifier = Modifier.clickable {
                    onItemClick(item)
                },
                bannerUrl = item.bannerUrl,
                title = item.title,
                description = item.description,
                timeAgo = item.timeAgo
            )
        }

        item { Spacer(modifier = Modifier.height(4.dp)) }
    }
}
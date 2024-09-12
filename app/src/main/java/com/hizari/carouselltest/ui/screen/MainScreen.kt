package com.hizari.carouselltest.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hizari.carouselltest.MainViewModel
import com.hizari.carouselltest.MainViewState
import com.hizari.carouselltest.R
import com.hizari.carouselltest.ui.component.DefaultEmptyComposable
import com.hizari.carouselltest.ui.component.DefaultErrorComposable
import com.hizari.carouselltest.ui.component.DefaultLoadingComposable
import com.hizari.carouselltest.ui.component.NewsListComposable
import com.hizari.carouselltest.ui.theme.CarousellTestTheme
import com.hizari.carouselltest.ui.theme.Red
import com.hizari.carouselltest.ui.theme.White
import com.hizari.common.util.Resources

@Preview(showBackground = true)
@Composable
fun PreviewMainScreenComposable() {
    CarousellTestTheme {
        MainScreenComposable()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenComposable(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState

    var showDropDownMenu by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Red,
                    titleContentColor = White
                ),
                title = {
                    Text(stringResource(R.string.app_name))
                },
                actions = {
                    IconButton(onClick = { showDropDownMenu = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more_white),
                            contentDescription = stringResource(R.string.options),
                            tint = White
                        )
                    }

                    DropdownMenu(
                        expanded = showDropDownMenu,
                        onDismissRequest = { showDropDownMenu = false },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                viewModel.loadNews(MainViewState.NewsType.Latest)
                                showDropDownMenu = false
                            },
                            text = { Text(stringResource(R.string.latest)) }
                        )
                        DropdownMenuItem(
                            onClick = {
                                viewModel.loadNews(MainViewState.NewsType.Popular)
                                showDropDownMenu = false
                            },
                            text = { Text(stringResource(R.string.popular)) }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            when (viewState.newsResource) {
                is Resources.Loading -> DefaultLoadingComposable(modifier = Modifier.padding(16.dp))
                is Resources.Error -> DefaultErrorComposable(
                    modifier = Modifier.padding(16.dp),
                    message = viewState.newsResource.throwable?.message.orEmpty(),
                    onRetry = { viewModel.loadNews(viewState.lastLoadNewsType) }
                )
                else -> {
                    val data = viewModel.viewState.value.newsResource.data.orEmpty()
                    if (data.isEmpty()) {
                        DefaultEmptyComposable(modifier = Modifier.padding(16.dp))
                    } else {
                        NewsListComposable(
                            items = data,
                            onItemClick = {  }
                        )
                    }
                }
            }
        }
    }
}
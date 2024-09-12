package com.hizari.carouselltest.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hizari.carouselltest.R
import com.hizari.carouselltest.ui.theme.CarousellTestTheme
import com.hizari.carouselltest.ui.theme.Red
import com.hizari.carouselltest.ui.theme.White

@Preview(showBackground = true)
@Composable
fun PreviewMainScreenComposable() {
    CarousellTestTheme {
        MainScreenComposable()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenComposable(modifier: Modifier = Modifier) {
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
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_more_white),
                            contentDescription = stringResource(R.string.options)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Text(
            text = "Hello Android!",
            modifier = Modifier.padding(innerPadding)
        )
    }
}
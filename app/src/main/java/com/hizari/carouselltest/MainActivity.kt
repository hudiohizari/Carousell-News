package com.hizari.carouselltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.hizari.carouselltest.ui.screen.MainScreen
import com.hizari.carouselltest.ui.theme.CarousellTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initApiCall()
        initComposeView()
    }

    private fun initApiCall() {
        viewModel.loadNews(MainViewState.NewsType.Recent)
    }

    private fun initComposeView() {
        enableEdgeToEdge()
        setContent {
            CarousellTestTheme {
                MainScreen()
            }
        }
    }

}
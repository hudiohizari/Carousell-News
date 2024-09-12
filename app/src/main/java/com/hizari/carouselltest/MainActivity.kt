package com.hizari.carouselltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hizari.carouselltest.ui.screen.MainScreenComposable
import com.hizari.carouselltest.ui.theme.CarousellTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarousellTestTheme {
                MainScreenComposable()
            }
        }
    }
}
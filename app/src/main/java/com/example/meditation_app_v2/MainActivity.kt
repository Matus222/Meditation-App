package com.example.meditation_app_v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.meditation_app_v2.screens.MeditationApp
import com.example.meditation_app_v2.ui.theme.Meditation_App_V2Theme
import java.util.concurrent.TimeUnit

/**
Hlavný súbor kde sa aplikácia spustí.

@author Matúš Kendera
 */

/**
Táto funkcia spustí aplikáciu.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Meditation_App_V2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MeditationApp(context = this)
                }
            }
        }
    }
}


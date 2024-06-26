package com.example.meditation_app_v2

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.meditation_app_v2.view_models.TimerDurationEntryViewModel
import com.example.meditation_app_v2.view_models.TimerViewModel

/**
 * Súbor kde sa inicializujú view modely
 */

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            TimerViewModel()
        }

        initializer {
            TimerDurationEntryViewModel()
        }
    }
}
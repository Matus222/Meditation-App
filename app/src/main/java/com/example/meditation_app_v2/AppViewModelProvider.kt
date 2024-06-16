package com.example.meditation_app_v2

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.meditation_app_v2.app_ui.TimerApplication
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
package com.example.meditation_app_v2.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.meditation_app_v2.data.TimerDurationItem

/**
Tento súbor reprezentuje viewModel pre obrazovku vytvárania časovača.

@author Matúš Kendera
 */

class TimerDurationEntryViewModel() : ViewModel() {
    var itemUiState by mutableStateOf(TimerDurationItemUiState())
        private set

    val items = List(1) {
        TimerDurationItemDetails(
            id = 0,
            hours = "0",
            minutes = "0",
            seconds = "10"
            )
    }.toMutableStateList()

    fun updateUiState(itemDetails: TimerDurationItemDetails) {
        itemUiState = TimerDurationItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            //timerDurationItemRepository.insertItem(itemUiState.itemDetails.toItem())

            items.add(
                TimerDurationItemDetails(
                    id = 0,
                    hours = itemUiState.itemDetails.hours,
                    minutes = itemUiState.itemDetails.minutes,
                    seconds = itemUiState.itemDetails.seconds,
                )
            )
        }
    }

    private fun validateInput(uiState: TimerDurationItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            hours.isNotBlank() || minutes.isNotBlank() || seconds.isNotBlank()
        }
    }
}

data class TimerDurationItemUiState(
    val itemDetails: TimerDurationItemDetails = TimerDurationItemDetails(),
    val isEntryValid: Boolean = false
)

data class TimerDurationItemDetails(
    val id: Int = 0,
    val hours: String = "",
    val minutes: String = "",
    val seconds: String = ""
)

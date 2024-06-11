package com.example.meditation_app_v2.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.meditation_app_v2.data.TimerDurationItem
import com.example.meditation_app_v2.data.TimerDurationItemRepository


//private val timerDurationItemRepository: TimerDurationItemRepository

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

fun TimerDurationItemDetails.toItem(): TimerDurationItem = TimerDurationItem(
    id = id,
    hours = hours.toIntOrNull() ?: 0,
    minutes = minutes.toIntOrNull() ?: 0,
    seconds = seconds.toIntOrNull() ?: 0
)

fun TimerDurationItem.toItemUiState(isEntryValid: Boolean = false): TimerDurationItemUiState = TimerDurationItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun TimerDurationItem.toItemDetails(): TimerDurationItemDetails = TimerDurationItemDetails(
    id = id,
    hours = hours.toString(),
    minutes = minutes.toString(),
    seconds = seconds.toString()
)


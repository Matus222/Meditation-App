package com.example.meditation_app_v2.app_ui

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.concurrent.timer

class TimerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TimerUiState())
    val uiState: StateFlow<TimerUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private lateinit var currentSound: Ringtone
    private var currentSeconds = _uiState.value.timerSeconds

    fun startTimer(context: Context) {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                _uiState.value = TimerUiState(timerMilliseconds = 1000 * currentSeconds)

                if (currentSeconds <= 0) {
                    currentSound = playSound(context)
                    currentSound.play()
                    break
                }

                delay(1000)
                currentSeconds--
            }
        }
    }

    private fun playSound(context: Context): Ringtone {
        val alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        return RingtoneManager.getRingtone(context, alarm)
    }

    fun pauseTimer() {
        timerJob?.cancel()

        if (this::currentSound.isInitialized) {
            currentSound.stop()
        }
    }

    fun stopTimer() {
        timerJob?.cancel()

        currentSeconds = _uiState.value.timerSeconds
        _uiState.value = TimerUiState(timerMilliseconds = 0)

        if (this::currentSound.isInitialized) {
            currentSound.stop()
        }
    }
}
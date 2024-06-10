package com.example.meditation_app_v2.app_ui

import android.content.Context
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditation_app_v2.R
import com.example.meditation_app_v2.menus.TimerAnimation
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer

class TimerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TimerUiState())
    val uiState: StateFlow<TimerUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var currentSeconds: Long = _uiState.value.timerSeconds
    private var savedSeconds = currentSeconds

    private var currentRingtone: Int = R.raw.ringtone3
    private lateinit var loadedRingtone: MediaPlayer

    fun startTimer(context: Context) {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                _uiState.value = TimerUiState(timerMilliseconds = 1000 * currentSeconds, timerSeconds = savedSeconds)

                if (currentSeconds <= 0) {
                    loadedRingtone = playSound(context)
                    loadedRingtone.start()
                    break
                }

                delay(1000)
                currentSeconds--
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()

        if (this::loadedRingtone.isInitialized) {
            loadedRingtone.stop()
        }
    }

    fun stopTimer() {
        timerJob?.cancel()

        currentSeconds = savedSeconds
        _uiState.value = TimerUiState(timerMilliseconds = 1000 * currentSeconds, timerSeconds = savedSeconds)

        if (this::loadedRingtone.isInitialized) {
            loadedRingtone.stop()
        }
    }

    fun changeRingtone(newRingtone: Int) {
        currentRingtone = newRingtone
    }

    fun changeTimerDuration(seconds: Long) {
        _uiState.value = TimerUiState(timerMilliseconds = 1000 * seconds, timerSeconds = seconds)

        savedSeconds = seconds
        currentSeconds = savedSeconds
    }

    fun formatTime(timerValue: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(timerValue)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timerValue) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timerValue) % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    private fun playSound(context: Context): MediaPlayer {
        return MediaPlayer.create(context, currentRingtone)
    }
}
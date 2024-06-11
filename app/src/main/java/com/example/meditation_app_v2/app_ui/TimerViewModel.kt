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

/**
Táto trieda reprezentuje viewModel tejto aplikácie.

@author Matúš Kendera
 */
class TimerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TimerUiState())
    val uiState: StateFlow<TimerUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var currentSeconds: Long = _uiState.value.timerSeconds
    private var savedSeconds = currentSeconds

    private var currentRingtone: Int = R.raw.ringtone3
    private lateinit var loadedRingtone: MediaPlayer

    /**
    Táto funkcia spustí časovač.
    Tento kód je inšpirovaný zo stránky: https://medium.com/@TippuFisalSheriff/creating-a-timer-screen-with-kotlin-and-jetpack-compose-in-android-f7c56952d599

     @param context je odkaz na kotnext tejto aplikácie.
     */
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

    /**
    Táto funkcia pauzne časovač.
     */
    fun pauseTimer() {
        timerJob?.cancel()

        if (this::loadedRingtone.isInitialized) {
            loadedRingtone.stop()
        }
    }

    /**
    Táto funkcia stopne a zresetuje časovač.
     */
    fun stopTimer() {
        timerJob?.cancel()

        currentSeconds = savedSeconds
        _uiState.value = TimerUiState(timerMilliseconds = 1000 * currentSeconds, timerSeconds = savedSeconds)

        if (this::loadedRingtone.isInitialized) {
            loadedRingtone.stop()
        }
    }

    /**
    Táto funkcia zmení terajšie zvonenie na nové.

     @param newRingtone je odkaz na nové zvonenie.
     */
    fun changeRingtone(newRingtone: Int) {
        currentRingtone = newRingtone
    }

    /**
    Táto funkcia zmení dĺžku časovača.

    @param seconds je nová dĺžka časovača v sekundách.
     */
    fun changeTimerDuration(seconds: Long) {
        _uiState.value = TimerUiState(timerMilliseconds = 1000 * seconds, timerSeconds = seconds)

        savedSeconds = seconds
        currentSeconds = savedSeconds
    }

    /**
    Táto funkcia formátuje hodnotu času v milisekundách na reálny čas.
    Táto funkcia je zo stránky: https://medium.com/@TippuFisalSheriff/creating-a-timer-screen-with-kotlin-and-jetpack-compose-in-android-f7c56952d599

    @param timerMilliseconds je dĺžka časovača v milisekundách.
    @return naformátovaný čas
     */
    fun formatTime(timerMilliseconds: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(timerMilliseconds)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timerMilliseconds) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timerMilliseconds) % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    /**
    Táto funkcia spustí zvonenie, keď časovač skončí.

    @param context je odkaz na kotnext tejto aplikácie.
    @return spustitelné zvonenie
     */
    private fun playSound(context: Context): MediaPlayer {
        return MediaPlayer.create(context, currentRingtone)
    }
}
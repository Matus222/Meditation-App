package com.example.meditation_app_v2.view_models

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditation_app_v2.R
import com.example.meditation_app_v2.app_ui.TimerUiState
import com.example.meditation_app_v2.data.TimerDurationItemRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
Táto trieda reprezentuje viewModel pre časovač tejto aplikácie.

@author Matúš Kendera
 */
class TimerViewModel() : ViewModel(), DefaultLifecycleObserver {
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
    Táto funkcia spustí zvonenie, keď časovač skončí.

    @param context je odkaz na kotnext tejto aplikácie.
    @return spustitelné zvonenie
     */
    private fun playSound(context: Context): MediaPlayer {
        return MediaPlayer.create(context, currentRingtone)
    }


    /**
     * Lifecycle funkcie na pauznutie a stopnutie časovača.
     */
    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        pauseTimer()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        pauseTimer()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        stopTimer()
    }
}

/**
 * Extension funkcia, ktorá spustí lifecycle observer a aplikácia začne počúvať eventy.
 */
@Composable
fun <viewModel : LifecycleObserver> viewModel.ObserveLifecycleEvents(lifecycle: Lifecycle) {
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(this@ObserveLifecycleEvents)
        onDispose {
            lifecycle.removeObserver(this@ObserveLifecycleEvents)
        }
    }
}
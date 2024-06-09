package com.example.meditation_app_v2.app_ui

data class TimerUiState(
    var timerSeconds: Long = 10,
    var timerMilliseconds: Long = 1000 * timerSeconds,
)
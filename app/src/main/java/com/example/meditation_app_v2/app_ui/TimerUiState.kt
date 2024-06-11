package com.example.meditation_app_v2.app_ui

/**
Táto trieda reprezentuje stav premenných, ktoré treba uchovať.

@author Matúš Kendera
 */

data class TimerUiState(
    var timerSeconds: Long = 10,
    var timerMilliseconds: Long = 1000 * timerSeconds,
)
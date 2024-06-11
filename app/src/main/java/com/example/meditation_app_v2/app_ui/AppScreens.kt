package com.example.meditation_app_v2.app_ui

import androidx.annotation.StringRes
import com.example.meditation_app_v2.R

/**
Táto trieda reprezentuje obrazovky tejto aplikácie.

@author Matúš Kendera
 */

enum class AppScreens(@StringRes val title: Int) {
    Main(title = R.string.main),
    Timer(title = R.string.timer),
    Ringtone(title = R.string.ringtone),
    TimerDuration(title = R.string.timerDuration),
    TimerDurationAdd(title = R.string.timerDurationAdd)
}
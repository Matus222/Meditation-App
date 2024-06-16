package com.example.meditation_app_v2.data

import java.util.concurrent.TimeUnit

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
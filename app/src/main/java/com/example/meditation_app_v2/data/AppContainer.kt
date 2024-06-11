package com.example.meditation_app_v2.data

import android.content.Context

interface AppContainer {
    val timerDurationItemRepository: TimerDurationItemRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val timerDurationItemRepository: TimerDurationItemRepository by lazy {
        OfflineTimerDurationItemRepository(TimerDurationDatabase.getDatabase(context).itemDao())
    }
}
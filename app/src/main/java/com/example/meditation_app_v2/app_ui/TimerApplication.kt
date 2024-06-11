package com.example.meditation_app_v2.app_ui

import android.app.Application
import com.example.meditation_app_v2.data.AppContainer
import com.example.meditation_app_v2.data.AppDataContainer

class TimerApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
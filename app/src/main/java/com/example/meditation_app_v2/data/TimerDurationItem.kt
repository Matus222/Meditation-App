package com.example.meditation_app_v2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timerDurationItems")
data class TimerDurationItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hours: Int,
    val minutes: Int,
    val seconds: Int
)
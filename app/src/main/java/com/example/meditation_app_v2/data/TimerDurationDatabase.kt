package com.example.meditation_app_v2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TimerDurationItem::class], version = 1, exportSchema = false)
abstract class TimerDurationDatabase : RoomDatabase() {

    abstract fun itemDao(): TimerDurationDao

    companion object {
        @Volatile
        private var Instance: TimerDurationDatabase? = null

        fun getDatabase(context: Context): TimerDurationDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TimerDurationDatabase::class.java, "timerDuration_Database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
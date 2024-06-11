package com.example.meditation_app_v2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerDurationDao {
    @Query("SELECT * from timerDurationItems ORDER BY hours ASC")
    fun getAllItems() : Flow<List<TimerDurationItem>>

    @Query("SELECT * from timerDurationItems WHERE id = :id")
    fun getItem(id: Int): Flow<TimerDurationItem>

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Upsert
    suspend fun insert(item: TimerDurationItem)

    @Update
    suspend fun update(item: TimerDurationItem)

    @Delete
    suspend fun delete(item: TimerDurationItem)
}
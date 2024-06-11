package com.example.meditation_app_v2.data

import kotlinx.coroutines.flow.Flow

interface TimerDurationItemRepository {
    fun getAllItemsStream(): Flow<List<TimerDurationItem>>

    fun getItemStream(id: Int): Flow<TimerDurationItem?>

    suspend fun insertItem(item: TimerDurationItem)

    suspend fun deleteItem(item: TimerDurationItem)

    suspend fun updateItem(item: TimerDurationItem)
}
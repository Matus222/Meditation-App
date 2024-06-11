package com.example.meditation_app_v2.data

import kotlinx.coroutines.flow.Flow

class OfflineTimerDurationItemRepository(private val timerDurationDao: TimerDurationDao): TimerDurationItemRepository {
    override fun getAllItemsStream(): Flow<List<TimerDurationItem>> = timerDurationDao.getAllItems()

    override fun getItemStream(id: Int): Flow<TimerDurationItem?> = timerDurationDao.getItem(id)

    override suspend fun insertItem(item: TimerDurationItem) = timerDurationDao.insert(item)

    override suspend fun deleteItem(item: TimerDurationItem) = timerDurationDao.delete(item)

    override suspend fun updateItem(item: TimerDurationItem) = timerDurationDao.update(item)
}
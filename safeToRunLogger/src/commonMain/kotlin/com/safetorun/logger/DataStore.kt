package com.safetorun.logger

import com.safetorun.logger.models.SafeToRunEvents
import kotlinx.coroutines.flow.Flow

internal interface DataStore {
    suspend fun store(data: SafeToRunEvents)
    suspend fun retrieve(): Flow<SafeToRunEvents>
    suspend fun delete(uuid: String)
    suspend fun clear()
}


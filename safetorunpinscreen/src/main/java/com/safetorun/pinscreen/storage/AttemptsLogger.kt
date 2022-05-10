package com.safetorun.pinscreen.storage

import com.safetorun.pinscreen.models.Attempts

internal interface AttemptsLogger {
    suspend fun logAttempt(attempts: Attempts)
    suspend fun getAttempts(): Attempts?
}

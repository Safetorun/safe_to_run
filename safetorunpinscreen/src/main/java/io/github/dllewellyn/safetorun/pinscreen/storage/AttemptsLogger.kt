package io.github.dllewellyn.safetorun.pinscreen.storage

import io.github.dllewellyn.safetorun.pinscreen.models.Attempts

internal interface AttemptsLogger {
    suspend fun logAttempt(attempts: Attempts)
    suspend fun getAttempts(): Attempts?
}

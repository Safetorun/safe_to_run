package io.github.dllewellyn.safetorun.pinscreen.models

internal interface AttemptsLogger {
    suspend fun logAttempt(attempts: Attempts)
    suspend fun getAttempts(): Attempts?
}

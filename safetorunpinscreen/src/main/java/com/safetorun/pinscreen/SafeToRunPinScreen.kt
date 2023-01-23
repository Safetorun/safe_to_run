package com.safetorun.pinscreen

import android.content.Context
import com.safetorun.pinscreen.models.RetryStrategy

/**
 * The safe to run pin screen. Use this class to control your PIN screen functionality
 */
class SafeToRunPinScreen internal constructor(
    private val storage: SafeToRunStorage,
    private val retryStrategy: RetryStrategy
) {
    suspend fun isPinSet() = haveSetPin(storage)
    suspend fun createOrUpdatePin(newPin: String) =
        setPin(newPin, storage) { throw NotImplementedError() }

    suspend fun checkPin(pinToCheck: String) = validatePin(
        pinToCheck, retryStrategy, storage, { throw NotImplementedError() }, storage
    )
}

/**
 * Safe to run pin screen builder - use this to build a safe to run pin screen
 */
class SafeToRunPinScreenBuilder internal constructor(private val context: Context) {
    /**
     * Retry strategy
     */
    var retryStrategy = retryStrategy { }

    internal fun build() = SafeToRunPinScreen(safeToRunPinStorage(context), retryStrategy)
}

/**
 * Safe to run pin screen
 *
 * @param context app context
 * @param builder a safe to run pin screen builder
 */
fun safeToRunPinScreen(context: Context, builder: SafeToRunPinScreenBuilder.() -> Unit) =
    SafeToRunPinScreenBuilder(context).run {
        builder()
        build()
    }

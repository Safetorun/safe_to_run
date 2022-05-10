package com.safetorun.pinscreen

import com.safetorun.pinscreen.storage.AttemptsLogger
import com.safetorun.pinscreen.models.Attempts
import com.safetorun.pinscreen.models.MaxAttemptsBehaviour
import com.safetorun.pinscreen.models.PinCheckResult
import com.safetorun.pinscreen.models.RetryStrategy
import com.safetorun.pinscreen.builder.RetryStrategyBuilder
import com.safetorun.pinscreen.models.HashedPin
import com.safetorun.pinscreen.storage.PinStorage

internal typealias PinHashFn = suspend (HashedPin) -> String

internal suspend fun haveSetPin(pinStorage: PinStorage) =
    pinStorage.retrievePin() != null

internal suspend fun setPin(
    pin: String,
    pinStorage: PinStorage,
    preStorageHasher: PinHashFn
) {
    pinStorage.clear()
    pinStorage.savePin(preStorageHasher(HashedPin(pin, pinStorage.retrieveOrCreateSalt())))
}

internal suspend fun validatePin(
    pin: String,
    retryStrategy: RetryStrategy,
    pinStorage: PinStorage,
    preStorageHasher: PinHashFn,
    attemptsLogger: AttemptsLogger
): PinCheckResult {
    return pinStorage.retrievePin()?.let {
        val attempts = attemptsLogger.getAttempts()?.attempts ?: 0

        if (attempts >= retryStrategy.attemptsBeforeLockout) {
            pinCheckError(retryStrategy, attempts)
        } else if (preStorageHasher(HashedPin(pin, pinStorage.retrieveOrCreateSalt())) == it) {
            attemptsLogger.logAttempt(Attempts(0))
            return PinCheckResult.PinAccepted
        } else {
            val totalAttempts = (attempts + 1)
            attemptsLogger.logAttempt(Attempts(totalAttempts))
            if (totalAttempts >= retryStrategy.attemptsBeforeLockout) {
                pinCheckError(retryStrategy, totalAttempts)
            } else {
                PinCheckResult.PinCheckError.PinIncorrect(retryStrategy.attemptsBeforeLockout - totalAttempts)
            }
        }
    } ?: PinCheckResult.PinCheckError.NoPinSet
}

private fun pinCheckError(
    retryStrategy: RetryStrategy,
    attempts: Int
) = when (val retry = retryStrategy.maxAttemptsBehaviour) {
    MaxAttemptsBehaviour.PermanentLockout -> PinCheckResult.PinCheckError.PermanentLockout(
        attempts
    )
    is MaxAttemptsBehaviour.ExponentialBackoff -> PinCheckResult.PinCheckError.TooManyAttempts(
        attempts,
        retry.backOffTime
    )
}

/**
 * Build a retry strategy
 *
 * @param bloc your configuration e.g.
 * ```
 * retryStrategy {
 *  attemptsBeforeLockout = 3
 *   maxAttemptsBehaviour = MaxAttemptsBehaviour.PermanentLockout
 * }
 * ```
 *
 * @see RetryStrategyBuilder for full information
 *
 */
fun retryStrategy(bloc: RetryStrategyBuilder.() -> Unit) =
    RetryStrategyBuilder().apply(bloc).build()

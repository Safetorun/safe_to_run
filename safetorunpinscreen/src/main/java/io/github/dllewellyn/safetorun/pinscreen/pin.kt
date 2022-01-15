package io.github.dllewellyn.safetorun.pinscreen

import io.github.dllewellyn.safetorun.pinscreen.models.Attempts
import io.github.dllewellyn.safetorun.pinscreen.models.AttemptsLogger
import io.github.dllewellyn.safetorun.pinscreen.models.MaxAttemptsBehaviour
import io.github.dllewellyn.safetorun.pinscreen.models.PinCheckResult
import io.github.dllewellyn.safetorun.pinscreen.models.RetryStrategy
import io.github.dllewellyn.safetorun.pinscreen.models.RetryStrategyBuilder

suspend fun haveSetPin(retrievePin: suspend () -> String?) =
    retrievePin() != null

suspend fun setPin(
    pin: String,
    storePin: suspend (String) -> Unit,
    preStorageHasher: suspend (String) -> String
) {
    storePin(preStorageHasher(pin))
}

suspend fun validatePin(
    pin: String,
    retryStrategy: RetryStrategy,
    retrievePin: suspend () -> String?,
    preStorageHasher: suspend (String) -> String,
    attemptsLogger: AttemptsLogger
): PinCheckResult {
    return retrievePin()?.let {
        val attempts = attemptsLogger.getAttempts()?.attempts ?: 0

        if (attempts >= retryStrategy.attemptsBeforeLockout) {
            pinCheckError(retryStrategy, attempts)
        } else if (preStorageHasher(pin) == it) {
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

fun retryStrategy(bloc: RetryStrategyBuilder.() -> Unit) =
    RetryStrategyBuilder().apply(bloc).build()

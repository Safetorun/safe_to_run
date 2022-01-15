package io.github.dllewellyn.safetorun.pinscreen.models

data class RetryStrategy internal constructor(
    val attemptsBeforeLockout: Int,
    val maxAttemptsBehaviour: MaxAttemptsBehaviour
)

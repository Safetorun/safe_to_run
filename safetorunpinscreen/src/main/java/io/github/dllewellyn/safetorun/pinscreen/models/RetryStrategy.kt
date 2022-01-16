package io.github.dllewellyn.safetorun.pinscreen.models

/**
 * The retry strategy
 */
data class RetryStrategy internal constructor(
    /**
     * The attempts before lockout
     */
    val attemptsBeforeLockout: Int,

    /**
     * The behaviour for max attempts @see MaxAttemptsBehaviour
     */
    val maxAttemptsBehaviour: MaxAttemptsBehaviour
)

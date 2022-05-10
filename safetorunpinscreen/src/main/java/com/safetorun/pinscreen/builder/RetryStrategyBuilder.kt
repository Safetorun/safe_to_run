package com.safetorun.pinscreen.builder

import com.safetorun.pinscreen.models.MaxAttemptsBehaviour
import com.safetorun.pinscreen.models.RetryStrategy

/**
 * Build a retry strategy
 */
class RetryStrategyBuilder internal constructor() {
    /**
     * The number of incorrect attempts before the account is locked out
     */
    var attemptsBeforeLockout = ATTEMPTS_BEFORE_LOCKOUT

    /**
     * The beavhiour to trigger when the max number of attempts has been hit
     */
    var maxAttemptsBehaviour: MaxAttemptsBehaviour = MaxAttemptsBehaviour.PermanentLockout

    internal fun build() = RetryStrategy(attemptsBeforeLockout, maxAttemptsBehaviour)

    companion object {
        private const val ATTEMPTS_BEFORE_LOCKOUT = 3
    }
}

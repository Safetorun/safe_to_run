package io.github.dllewellyn.safetorun.pinscreen.models

class RetryStrategyBuilder internal constructor() {
    var attemptsBeforeLockout = ATTEMPTS_BEFORE_LOCKOUT
    var maxAttemptsBehaviour: MaxAttemptsBehaviour = MaxAttemptsBehaviour.PermanentLockout

    internal fun build() = RetryStrategy(attemptsBeforeLockout, maxAttemptsBehaviour)

    companion object {
        private const val ATTEMPTS_BEFORE_LOCKOUT = 3
    }
}

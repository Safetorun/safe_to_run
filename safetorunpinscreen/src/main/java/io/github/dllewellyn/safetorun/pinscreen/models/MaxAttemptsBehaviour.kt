package io.github.dllewellyn.safetorun.pinscreen.models

sealed class MaxAttemptsBehaviour {
    object PermanentLockout : MaxAttemptsBehaviour()
    data class ExponentialBackoff(val backOffTime: Long) : MaxAttemptsBehaviour()
}

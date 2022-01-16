package io.github.dllewellyn.safetorun.pinscreen.models

/**
 * Behaviour for when max attempts have been reached
 */
sealed class MaxAttemptsBehaviour {

    /**
     * Permanently lock out if max attempts have been rached
     */
    object PermanentLockout : MaxAttemptsBehaviour()

    /**
     * Back off exponentially - this works by taking the backoff time provided
     * and increasing the amount of time for every attempt. E.g. first failed
     * attempt will be `backOffTime` second will be `backOffTime * 3` etc
     */
    data class ExponentialBackoff(val backOffTime: Long) : MaxAttemptsBehaviour()
}

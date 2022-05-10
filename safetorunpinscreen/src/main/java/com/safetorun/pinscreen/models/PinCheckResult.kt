package com.safetorun.pinscreen.models

/**
 * The result of a pin check
 */
sealed class PinCheckResult {
    /**
     * Indicates an error in the pin check
     */
    sealed class PinCheckError : PinCheckResult() {
        /**
         * Happens when your pin is not set
         */
        object NoPinSet : PinCheckError()

        /**
         * Returned when the incorrect pin was enetered
         *
         * @param remainingAttempts the numbeer of attempts remaining before
         * max retry behaviour is initiated @see MaxAttemptsBehaviour
         */
        data class PinIncorrect(val remainingAttempts: Int) : PinCheckError()

        /**
         * Too many attempts have happened
         *
         * @param attempts number of attempts has been exceeded
         * @param retryIn amount of time to wait before retrying
         */
        data class TooManyAttempts(val attempts: Int, val retryIn: Long) : PinCheckError()

        /**
         * Permanent lockout - this account is permanently locked out.
         *
         * @param attempts number of attempts the user has made
         */
        data class PermanentLockout(val attempts: Int) : PinCheckError()
    }

    /**
     * Indicates your PIN has been accepted
     */
    object PinAccepted : PinCheckResult()
}

package io.github.dllewellyn.safetorun.pinscreen.models

sealed class PinCheckResult {
    sealed class PinCheckError : PinCheckResult() {
        object NoPinSet : PinCheckError()
        data class PinIncorrect(val remainingAttempts: Int) : PinCheckError()
        data class TooManyAttempts(val attempts: Int, val retryIn: Long) : PinCheckError()
        data class PermanentLockout(val attempts: Int) : PinCheckError()
    }

    object PinAccepted : PinCheckResult()
}

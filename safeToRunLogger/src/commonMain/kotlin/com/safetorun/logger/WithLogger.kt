package com.safetorun.logger

import com.safetorun.intents.SafeToRunVerifier

/**
 * Returns a function that wraps the given function with logging capabilities.
 * This function is an extension of the [SafeToRunVerifier] interface.
 *
 * @param <T> The type of the input parameter of the function.
 * @param logValue A flag indicating whether to log the value or not.
 * @param logger A function accepting a boolean result and a string value for logging purposes.
 *
 * @return The wrapped function with logging capabilities.
 */
fun <T> SafeToRunVerifier<T>.withLogger(
    logValue: Boolean = false,
    logger: (Boolean, String?) -> Unit
): SafeToRunVerifier<T> {
    this.andThen { b, t ->
        logger(b, if (logValue) t.toString() else null)
    }

    return this
}

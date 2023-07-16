package com.safetorun.intents.logger

/**
 * Returns a function that wraps the given function with logging capabilities.
 *
 * @param <T> The type of the input parameter of the function.
 * @param logValue A flag indicating whether to log the value or not.
 * @param logger A function accepting a boolean result and a string value for logging purposes.
 * @param invoke The function to be wrapped with logging capabilities.
 * @return The wrapped function with logging capabilities.
 */
inline fun <T> ((T) -> Boolean).withLogger(
    logValue: Boolean = false,
    crossinline logger: (Boolean, String?) -> Unit
): (T) -> Boolean {
    return { value ->
        invoke(value).also { pass ->
            if (logValue) {
                logger(pass, value.toString())
            } else {
                logger(pass, null)
            }
        }
    }
}

/**
 * Returns a function that wraps the given function with logging capabilities.
 *
 * @param <T> The type of the input parameter of the function.
 * @param logValue A flag indicating whether to log the value or not.
 * @param logger A function accepting a boolean result and a string value for logging purposes.
 * @return The wrapped function with logging capabilities.
 */
inline fun <T> withLogger(
    logValue: Boolean = false,
    crossinline logger: (Boolean, String?) -> Unit,
    crossinline invoke: (T) -> Boolean
): (T) -> Boolean {
    return invoke.withLogger(logValue, logger)
}

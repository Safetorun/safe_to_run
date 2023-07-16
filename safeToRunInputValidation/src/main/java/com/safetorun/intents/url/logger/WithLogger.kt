package com.safetorun.intents.url.logger

inline fun <T> ((T) -> Boolean).withLogger(
    verbose: Boolean = false,
    crossinline logger: (Boolean, String?) -> Unit
): (T) -> Boolean {
    return { value ->
        invoke(value).also { pass ->
            if (verbose) {
                logger(pass, value.toString())
            } else {
                logger(pass, value.toString())
            }
        }
    }
}

inline fun <T> withLogger(
    verbose: Boolean = false,
    crossinline logger: (Boolean, String?) -> Unit,
    crossinline invoke: (T) -> Boolean
): (T) -> Boolean {
    return invoke.withLogger(verbose, logger)
}

package com.safetorun.logger

import android.content.Intent
import com.safetorun.intents.SafeToRunVerifier
import com.safetorun.logger.models.VerifyType
import java.io.File

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
inline fun <reified T> SafeToRunVerifier<T>.withLogger(
    logValue: Boolean = false,
    crossinline logger: (Boolean, VerifyType, String?) -> Unit
): SafeToRunVerifier<T> {

    val verifiedTyped = when (T::class) {
        Intent::class -> VerifyType.Intent
        String::class -> VerifyType.Url
        File::class -> VerifyType.File
        else -> VerifyType.Other
    }

    this.andThen { b, t ->
        logger(b, verifiedTyped, if (logValue) t.toString() else null)
    }

    return this
}

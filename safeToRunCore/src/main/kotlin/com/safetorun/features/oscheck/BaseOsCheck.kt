package com.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.conditional.ConditionalResponse

/**
 * Run an OS check
 *
 * @param failureMessage a function to build the failure message
 * @param failureCondition the function to run to determine if we should fail or not
 */
inline fun OSInformationQuery.baseOsCheck(
    crossinline failureMessage: () -> String,
    crossinline failureCondition: OSInformationQuery.() -> Boolean
) = Conditional {
    ConditionalResponse(failed = with(this) { failureCondition() })
        .messageIfFailed(failureMessage())
}

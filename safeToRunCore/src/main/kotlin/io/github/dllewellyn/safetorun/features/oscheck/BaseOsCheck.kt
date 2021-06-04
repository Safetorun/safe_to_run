package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.conditional.ConditionalResponse

private class BaseOsCheck(
    private val query: OSInformationQuery,
    private val failureMessage: () -> String,
    private val failureCondition: OSInformationQuery.() -> Boolean
) :
    Conditional {

    override fun invoke() =
        ConditionalResponse(failed = with(query) { failureCondition() })
            .messageIfFailed(failureMessage())
}

/**
 * Run an OS check
 *
 * @param failureMessage a function to build the failure message
 * @param failureCondition the function to run to determine if we should fail or not
 */
fun OSInformationQuery.baseOsCheck(
    failureMessage: () -> String,
    failureCondition: OSInformationQuery.() -> Boolean
): Conditional {
    return BaseOsCheck(this, failureMessage, failureCondition)
}

package com.safetorun.features.debug

import com.safetorun.checks.SafeToRunCheck

/**
 * Create a debug check
 *
 * @param isDebuggable the query object to use to determine if the app is debuggable
 * @param debuggableStrings the strings to use for errors or successes
 */
fun debugCheckConfiguration(
    isDebuggable: IsDebuggable,
    debuggableStrings: DebuggableStrings
): SafeToRunCheck {
    return DebuggableCheck(isDebuggable, debuggableStrings)
}

package io.github.dllewellyn.safetorun.features.debug

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

fun debugCheckConfiguration(
    isDebuggable: IsDebuggable,
    debuggableStrings: DebuggableStrings
): SafeToRunCheck {
    return DebuggableCheck(isDebuggable, debuggableStrings)
}

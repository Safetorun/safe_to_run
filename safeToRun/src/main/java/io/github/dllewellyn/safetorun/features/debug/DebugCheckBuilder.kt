package io.github.dllewellyn.safetorun.features.debug

import android.content.Context
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

/**
 * Add a debug check to warn or error if there is a debugger attached or if the app is debuggable
 *
 * ```
 * this warnIf debugCheck()
 * ```
 */
fun Context.debugCheck(): SafeToRunCheck {
    return DebuggableCheck(AndroidIsDebuggable(this), AndroidDebuggableStrings(this))
}
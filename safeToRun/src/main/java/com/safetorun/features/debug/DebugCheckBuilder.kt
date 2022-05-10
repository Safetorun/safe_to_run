package com.safetorun.features.debug

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Debug
import com.safetorun.checks.SafeToRunCheck

/**
 * Add a debug check to warn or error if there is a debugger attached or if the app is debuggable
 *
 * ```
 * debugCheck().warn()
 * ```
 * @receiver Android context - call this from app, fragment or activity
 */
fun Context.debugCheck(): SafeToRunCheck {
    return debugCheckConfiguration(AndroidIsDebuggable(this), AndroidDebuggableStrings(this))
}

/**
 * Check if the app is debuggable
 *
 * @return true if debuggable (and should fail if true)
 */
inline fun Context.isDebuggableCheck() =
    applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

/**
 * Check if a debugger is attached
 *
 * @return true if a debugger is attached (and should fail if true)
 */
inline fun isDebuggerAttachedCheck() =
    Debug.isDebuggerConnected() || Debug.waitingForDebugger()

/**
 * Ban debugging check
 *
 * @return check if the app is debuggable or if a debugger is attached
 */
inline fun Context.banDebugCheck() =
    isDebuggerAttachedCheck() || isDebuggableCheck()

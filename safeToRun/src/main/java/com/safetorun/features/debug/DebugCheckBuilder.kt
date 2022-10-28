package com.safetorun.features.debug

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Debug


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

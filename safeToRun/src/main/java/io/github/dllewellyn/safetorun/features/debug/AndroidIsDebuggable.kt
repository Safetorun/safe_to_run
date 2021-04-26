package io.github.dllewellyn.safetorun.features.debug

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Debug

internal class AndroidIsDebuggable(private val context: Context) : IsDebuggable {

    override fun isDebuggable(): Boolean {
        return context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }

    override fun isDebuggerAttached(): Boolean {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger()
    }
}
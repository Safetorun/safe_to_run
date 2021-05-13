package io.github.dllewellyn.safetorun.features.debug

interface IsDebuggable {
    fun isDebuggable(): Boolean
    fun isDebuggerAttached(): Boolean
}

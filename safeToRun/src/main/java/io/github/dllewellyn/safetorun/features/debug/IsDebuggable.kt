package io.github.dllewellyn.safetorun.features.debug

internal interface IsDebuggable {
    fun isDebuggable() : Boolean
    fun isDebuggerAttached() : Boolean
}
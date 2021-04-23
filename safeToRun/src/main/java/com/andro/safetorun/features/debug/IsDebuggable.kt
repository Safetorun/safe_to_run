package com.andro.safetorun.features.debug

interface IsDebuggable {
    fun isDebuggable() : Boolean
    fun isDebuggerAttached() : Boolean
}
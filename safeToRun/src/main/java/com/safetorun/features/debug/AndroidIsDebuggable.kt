package com.safetorun.features.debug

import android.content.Context

internal class AndroidIsDebuggable(private val context: Context) : IsDebuggable {

    override fun isDebuggable(): Boolean {
        return context.isDebuggableCheck()
    }

    override fun isDebuggerAttached(): Boolean {
        return isDebuggerAttachedCheck()
    }
}

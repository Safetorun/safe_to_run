package com.andro.safetorun.features.debug

import android.content.Context
import com.andro.safetorun.R
import com.andro.safetorun.reporting.BaseAndroidStrings

class AndroidDebuggableStrings(context: Context) : BaseAndroidStrings(context),
    DebuggableStrings {

    override fun appIsDebuggableMessage(): String {
        return resources.getString(R.string.debuggable)
    }

    override fun appIsNotDebuggableMessage(): String {
        return resources.getString(R.string.not_debuggable)

    }

    override fun debuggerAttachedMessage(): String {
        return resources.getString(R.string.debugger_attached)

    }

    override fun debuggerNotAttachedMessage(): String {
        return resources.getString(R.string.debugger_not_attached)
    }
}
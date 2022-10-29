package com.safetorun.features.debug

import android.content.res.Resources
import com.safetorun.R
import io.mockk.every
import io.mockk.mockk

object AndroidDebuggableStringsSample {
    const val DEBUGGER_ATTACHED = "Debugger attached"
    const val DEBUGGER_NOT_ATTACHED = "Debugger not attached"
    const val DEBUGGABLE = "Debuggable"
    const val NOT_DEBUGGABLE = "Not debuggable"

    fun setupAMockResources(): Resources {
        val resources = mockk<Resources>()

        every { resources.getString(R.string.debuggable) } returns DEBUGGABLE
        every { resources.getString(R.string.not_debuggable) } returns NOT_DEBUGGABLE
        every { resources.getString(R.string.debugger_attached) } returns DEBUGGER_ATTACHED
        every { resources.getString(R.string.debugger_not_attached) } returns DEBUGGER_NOT_ATTACHED

        return resources
    }
}

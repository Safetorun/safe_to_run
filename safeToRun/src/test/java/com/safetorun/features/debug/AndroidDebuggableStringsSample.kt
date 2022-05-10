package com.safetorun.features.debug

import android.content.res.Resources
import io.github.dllewellyn.safetorun.R
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
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

    fun mapReportsToMessages(safeToRunReport: SafeToRunReport): List<String> {
        return when (safeToRunReport) {
            is SafeToRunReport.MultipleReports -> mapReportsToMessages(safeToRunReport)
            is SafeToRunReport.SafeToRunReportFailure -> listOf(safeToRunReport.failureMessage)
            is SafeToRunReport.SafeToRunReportSuccess -> listOf(safeToRunReport.successMessage)
            is SafeToRunReport.SafeToRunWarning -> listOf(safeToRunReport.warnReason)
            else -> {
                // Impossible. What's your problem intellij?
                emptyList()
            }
        }
    }
}

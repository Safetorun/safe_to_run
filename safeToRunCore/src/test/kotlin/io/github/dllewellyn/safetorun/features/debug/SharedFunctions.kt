package io.github.dllewellyn.safetorun.features.debug

import io.github.dllewellyn.safetorun.reporting.SafeToRunReport

object DebuggableStringsSample {
    const val DEBUGGER_ATTACHED = "Debugger attached"
    const val DEBUGGER_NOT_ATTACHED = "Debugger not attached"
    const val DEBUGGABLE = "Debuggable"
    const val NOT_DEBUGGABLE = "Not debuggable"


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
package io.github.dllewellyn.safetorun.features.debug

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport

class DebuggableCheck(
    private val isDebuggable: IsDebuggable,
    private val debuggableStrings: DebuggableStrings
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {

        val debuggable = if (isDebuggable.isDebuggable()) {
            SafeToRunReport.SafeToRunReportFailure(DEBUGGABLE_FAILURE, debuggableStrings.appIsDebuggableMessage())
        } else {
            SafeToRunReport.SafeToRunReportSuccess(debuggableStrings.appIsNotDebuggableMessage())
        }

        val debuggerAttached = if (isDebuggable.isDebuggerAttached()) {
            SafeToRunReport.SafeToRunReportFailure(
                DEBUGGER_ATTACHED_FAILURE,
                debuggableStrings.debuggerAttachedMessage()
            )
        } else {
            SafeToRunReport.SafeToRunReportSuccess(debuggableStrings.debuggerNotAttachedMessage())
        }

        return SafeToRunReport.MultipleReports(listOf(debuggable, debuggerAttached))
    }

    companion object {
        const val DEBUGGABLE_FAILURE = "db-f"
        const val DEBUGGER_ATTACHED_FAILURE = "dba-f"
    }
}
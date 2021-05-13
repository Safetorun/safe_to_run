package io.github.dllewellyn.safetorun.checks

import io.github.dllewellyn.safetorun.reporting.SafeToRunReport

internal class CompositeSafeToRunCheck(
    private val safeToRunChecks: List<SafeToRunCheck>,
    private val warnOnlyChecks: List<SafeToRunCheck>
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        return SafeToRunReport.MultipleReports(
            listOf(callFailOns(), callWarnings()).flatten()
        )
    }

    private fun callFailOns() = safeToRunChecks.map {
        it.canRun()
    }

    private fun callWarnings() = warnOnlyChecks.map {
        convertSafeToRunReportToWarning(it.canRun())
    }

    private fun convertSafeToRunReportToWarning(result: SafeToRunReport): SafeToRunReport = when (result) {
        is SafeToRunReport.SafeToRunReportFailure -> SafeToRunReport.SafeToRunWarning(
            result.failureReason,
            result.failureMessage
        )
        is SafeToRunReport.MultipleReports -> SafeToRunReport.MultipleReports(
            result.reports.map {
                convertSafeToRunReportToWarning(it)
            }
        )
        else -> result
    }
}

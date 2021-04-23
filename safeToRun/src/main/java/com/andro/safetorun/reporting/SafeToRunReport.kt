package com.andro.safetorun.reporting


sealed class SafeToRunReport {
    data class SafeToRunReportFailure(val failureReason: String, val failureMessage: String) : SafeToRunReport()
    data class SafeToRunReportSuccess(val successMessage: String) : SafeToRunReport()
    data class MultipleReports(val reports: List<SafeToRunReport>) : SafeToRunReport()
    data class SafeToRunWarning(val warnReason: String, val warningMessage: String) : SafeToRunReport()
}


fun List<SafeToRunReport>.toMultipleReport() = SafeToRunReport.MultipleReports(this)
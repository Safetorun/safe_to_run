package io.github.dllewellyn.safetorun.reporting


sealed class SafeToRunReport {
    data class SafeToRunReportFailure(val failureReason: String, val failureMessage: String) : SafeToRunReport()
    data class SafeToRunReportSuccess(val successMessage: String) : SafeToRunReport()
    data class MultipleReports(val reports: List<SafeToRunReport>) : SafeToRunReport()
    data class SafeToRunWarning(val warnReason: String, val warningMessage: String) : SafeToRunReport()
}

data class GroupedSafeToRunReports(
    val failedReports: List<SafeToRunReport.SafeToRunReportFailure>,
    val warningReports: List<SafeToRunReport.SafeToRunWarning>,
    val successReports: List<SafeToRunReport.SafeToRunReportSuccess>
)

fun SafeToRunReport.toGrouped(): GroupedSafeToRunReports {
    return with(flattenAndExtract(this)) {
        GroupedSafeToRunReports(
            failedReports = second,
            warningReports = third,
            successReports = first
        )
    }
}

private fun flattenAndExtract(safeToRunReport: SafeToRunReport): Triple<List<SafeToRunReport.SafeToRunReportSuccess>, List<SafeToRunReport.SafeToRunReportFailure>, List<SafeToRunReport.SafeToRunWarning>> {
    val failures = mutableListOf<SafeToRunReport.SafeToRunReportFailure>()
    val success = mutableListOf<SafeToRunReport.SafeToRunReportSuccess>()
    val warnings = mutableListOf<SafeToRunReport.SafeToRunWarning>()

    when (safeToRunReport) {
        is SafeToRunReport.MultipleReports -> {
            safeToRunReport.reports.forEach {
                with(flattenAndExtract(it)) {
                    success.addAll(first)
                    failures.addAll(second)
                    warnings.addAll(third)
                }
            }
        }
        is SafeToRunReport.SafeToRunReportFailure -> {
            failures.add(safeToRunReport)
        }
        is SafeToRunReport.SafeToRunReportSuccess -> {
            success.add(safeToRunReport)
        }
        is SafeToRunReport.SafeToRunWarning -> {
            warnings.add(safeToRunReport)
        }
    }

    return Triple(success, failures, warnings)
}

fun List<SafeToRunReport>.toMultipleReport() = SafeToRunReport.MultipleReports(this)
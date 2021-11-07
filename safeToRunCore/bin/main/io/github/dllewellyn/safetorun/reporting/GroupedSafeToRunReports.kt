package io.github.dllewellyn.safetorun.reporting

/**
 * A group of safe to run reports that are grouped together by success, warnings
 * and failures
 *
 * @param failedReports reports of safe to run checks which failed the check
 * @param warningReports reports of safe to run checks which failed but were only added as warnings
 * @param successReports reports of safe to run checks which passed
 */
data class GroupedSafeToRunReports(
    val failedReports: List<SafeToRunReport.SafeToRunReportFailure>,
    val warningReports: List<SafeToRunReport.SafeToRunWarning>,
    val successReports: List<SafeToRunReport.SafeToRunReportSuccess>
)

/**
 * Convert a safe to run report to a grouped safe to run reports
 */
fun SafeToRunReport.toGrouped(): GroupedSafeToRunReports {
    return with(flattenAndExtract(this)) {
        GroupedSafeToRunReports(
            failedReports = second,
            warningReports = third,
            successReports = first
        )
    }
}

private fun flattenAndExtract(safeToRunReport: SafeToRunReport): Triple<
        List<SafeToRunReport.SafeToRunReportSuccess>,
        List<SafeToRunReport.SafeToRunReportFailure>,
        List<SafeToRunReport.SafeToRunWarning>> {
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

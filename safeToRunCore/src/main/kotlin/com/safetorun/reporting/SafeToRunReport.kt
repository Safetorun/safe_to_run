package com.safetorun.reporting

/**
 * Class which produces the results of a safe to run check
 */
sealed class SafeToRunReport {

    /**
     * Safe to run report failure. Used when a check fails
     *
     * @param failureMessage a human readable message
     * @param failureReason a predefined constant that can be used by a tool to determine failure reason
     */
    data class SafeToRunReportFailure(val failureReason: String, val failureMessage: String) : SafeToRunReport()

    /**
     * Safe to run report success
     *
     * @param successMessage a human readable message
     */
    data class SafeToRunReportSuccess(val successMessage: String) : SafeToRunReport()

    /**
     * A combination of safe to run reports
     *
     * @param reports the reports for the results
     */
    data class MultipleReports(val reports: List<SafeToRunReport>) : SafeToRunReport()

    /**
     * Safe to run warning
     *
     * @param warnReason the reason for the warning
     * @param warningMessage a predefined constant that can be used by a tool to determine failure reason
     */
    data class SafeToRunWarning(val warnReason: String, val warningMessage: String) : SafeToRunReport()
}

/**
 * Determine if there are any failures
 *
 * @receiver a safe to run report
 *
 * @return true if there are any failures, false otherwise
 */
fun SafeToRunReport.anyFailures() = toGrouped().failedReports.isNotEmpty()

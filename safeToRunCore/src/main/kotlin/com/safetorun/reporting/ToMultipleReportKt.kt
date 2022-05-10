package com.safetorun.reporting

/**
 * Convert a list of safe to run reports to multiple response
 *
 * @receiver a list of Safe to run reports
 *
 * @return an instance of [SafeToRunReport.MultipleReports]
 */
fun List<SafeToRunReport>.toMultipleReport() = SafeToRunReport.MultipleReports(this)

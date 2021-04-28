package io.github.dllewellyn.safetorun.reporting

internal fun List<SafeToRunReport>.toMultipleReport() = SafeToRunReport.MultipleReports(this)
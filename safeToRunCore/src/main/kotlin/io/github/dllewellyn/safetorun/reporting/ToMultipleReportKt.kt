package io.github.dllewellyn.safetorun.reporting

fun List<SafeToRunReport>.toMultipleReport() = SafeToRunReport.MultipleReports(this)

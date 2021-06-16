---
id: safetorunreporting
title: Safe to run reporting
slug: /safetorunreporting
---

### Errors and Warns

Safe to run reporting has the ability to report something as an 'error' or as a 'warning'. 
This provides the ability for us to fail a check if the error preconditions are not met,
or simply to return a warning if they do not pass. For example you might want to throw
a runtime exception, or else disable certain features of your app on error, or simply
log if you see a warning

### Reports

Safe to run checks return a report. This is a sealed class with the following definition


```kotlin

sealed class SafeToRunReport {
    data class SafeToRunReportFailure(val failureReason: String, val failureMessage: String) : SafeToRunReport()
    data class SafeToRunReportSuccess(val successMessage: String) : SafeToRunReport()
    data class MultipleReports(val reports: List<SafeToRunReport>) : SafeToRunReport()
    data class SafeToRunWarning(val warnReason: String, val warningMessage: String) : SafeToRunReport()
}
```

You can check the result with something like this:


```kotlin
val safeToRun = SafeToRun.isSafeToRun()
throwIfSomethingFailed(safeToRun)

fun throwIfSomethingFailed(safeToRunReport: SafeToRunReport) {
    when (safeToRunReport) {
        is SafeToRunReport.MultipleReports -> safeToRunReport.reports.forEach(::throwIfSomethingFailed)
        is SafeToRunReport.SafeToRunReportFailure -> throw RuntimeException(safeToRunReport.failureMessage)
        is SafeToRunReport.SafeToRunReportSuccess ->{} // Nothing
        is SafeToRunReport.SafeToRunWarning -> {} // It's a good idea to log
    }
}
```

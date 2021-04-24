---
id: why
title: Why Safe to Run?
slug: /
---

## Motivation

Safe to run has been developed in order to simplify 
development of secure android applications. In particular
the goal of the project is to provide a simple, configurable
way for app developers to define when an app should or
should not run and a simple way of calling that check.

## Terms

### Checks

Safe to run consists of a number of 'checks' which are detailed
in the documentation. These checks can be configured to 'error' or 
'warn'. What you do in response to a 'failed' check is up to you - for example
you might want to throw a runtime exception, or else disable certain features
of your app

### Reports 

Safe to run checks return a report. This is a sealed class with the following
definition 


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


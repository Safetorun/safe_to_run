package com.andro.safetorun.checks

import com.andro.safetorun.reporting.SafeToRunReport

internal class CompositeSafeToRunCheck(private val safeToRunChecks: List<SafeToRunCheck>) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        return SafeToRunReport.MultipleReports(
            safeToRunChecks.map {
                it.canRun()
            }
        )
    }
}
package com.andro.safetorun.checks

import android.content.Context
import com.andro.safetorun.reporting.SafeToRunReport

internal class CompositeSafeToRunCheck(private val safeToRunChecks: List<SafeToRunCheck>) : SafeToRunCheck {

    override fun canRun(context: Context): SafeToRunReport {
        return SafeToRunReport.MultipleReports(
            safeToRunChecks.map {
                it.canRun(context)
            }
        )
    }
}
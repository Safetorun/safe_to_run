package com.andro.safetorun.features.blacklistedapps

import android.content.Context
import com.andro.safetorun.R
import com.andro.safetorun.checks.SafeToRunCheck
import com.andro.safetorun.reporting.SafeToRunReport
import com.andro.safetorun.reporting.toMultipleReport

internal class BlacklistedApplicationDetection(
    private val allFiles: List<String>,
    private val blacklistedAppCheck: BlacklistedAppCheck
) : SafeToRunCheck {

    override fun canRun(context: Context): SafeToRunReport {
        val blacklistedAppsPresent = allFiles.filter {
            blacklistedAppCheck.isAppPresent(it)
        }

        return if (blacklistedAppsPresent.isEmpty()) {
            createSafeToRunSuccessMessage(context)
        } else {
            blacklistedAppsPresent
                .map { context.createSafeToRunReportFailure(it) }
                .toMultipleReport()
        }
    }

    private fun createSafeToRunSuccessMessage(context: Context) =
        SafeToRunReport.SafeToRunReportSuccess(context.resources.getString(R.string.no_blacklisted_apps_found))

    private fun Context.createSafeToRunReportFailure(
        it: String
    ) = SafeToRunReport.SafeToRunReportFailure(
        BLACKLISTED_APP_ERROR_CODE,
        resources.getString(R.string.found_blacklisted_app, it)
    )


    companion object {
        const val BLACKLISTED_APP_ERROR_CODE = "bl-app"
    }
}


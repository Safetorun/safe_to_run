package com.andro.safetorun.features.blacklistedapps

import com.andro.safetorun.checks.SafeToRunCheck
import com.andro.safetorun.reporting.SafeToRunReport
import com.andro.safetorun.reporting.toMultipleReport


internal class BlacklistedApplicationDetection(
    private val allFiles: List<String>,
    private val blacklistedAppCheck: BlacklistedAppCheck,
    private val blacklistedAppStrings: BlacklistedAppStrings
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        val blacklistedAppsPresent = allFiles.filter {
            blacklistedAppCheck.isAppPresent(it)
        }

        return if (blacklistedAppsPresent.isEmpty()) {
            createSafeToRunSuccessMessage()
        } else {
            blacklistedAppsPresent
                .map { createSafeToRunReportFailure(it) }
                .toMultipleReport()
        }
    }

    private fun createSafeToRunSuccessMessage() =
        SafeToRunReport.SafeToRunReportSuccess(blacklistedAppStrings.didNotFindBlacklistedAppMessage())

    private fun createSafeToRunReportFailure(
        foundResult: String
    ) = SafeToRunReport.SafeToRunReportFailure(
        BLACKLISTED_APP_ERROR_CODE,
        blacklistedAppStrings.foundBlacklistedAppMessage(foundResult)
    )


    companion object {
        const val BLACKLISTED_APP_ERROR_CODE = "bl-app"
    }
}


package com.safetorun.features.blacklistedapps

import com.safetorun.checks.SafeToRunCheck
import com.safetorun.reporting.SafeToRunReport
import com.safetorun.reporting.toMultipleReport


internal class BlacklistedApplicationDetection(
    private val blacklistedApplications: List<String>,
    private val blacklistedAppCheck: BlacklistedAppCheck,
    private val blacklistedAppStrings: BlacklistedAppStrings
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        val blacklistedAppsPresent = blacklistedApplications.filter {
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

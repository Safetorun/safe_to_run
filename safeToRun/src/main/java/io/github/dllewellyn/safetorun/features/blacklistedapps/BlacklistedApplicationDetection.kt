package io.github.dllewellyn.safetorun.features.blacklistedapps

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.github.dllewellyn.safetorun.reporting.toMultipleReport


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


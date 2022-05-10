package com.safetorun.features.oscheck

import com.safetorun.checks.SafeToRunCheck
import com.safetorun.conditional.ConditionalResponse
import com.safetorun.reporting.SafeToRunReport
import com.safetorun.reporting.toMultipleReport

internal class OSDetectionCheck(
    private val osDetectionConfig: OSDetectionConfig,
    private val osDetectionStrings: OSDetectionStrings
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        val results = osDetectionConfig.bannedOsResult
            .map { conditional -> conditional() }
            .filter { result -> result.failed }
            .map { result -> createSafeToRunReportFailure(result) }

        return if (results.isEmpty()) {
            createSafeToRunReportSuccess()
        } else {
            results.toList().toMultipleReport()
        }
    }

    private fun createSafeToRunReportFailure(
        result: ConditionalResponse
    ): SafeToRunReport.SafeToRunReportFailure {
        return SafeToRunReport.SafeToRunReportFailure(
            ERROR_CODE,
            result.message ?: osDetectionStrings.genericFailureMessage()
        )
    }

    private fun createSafeToRunReportSuccess() =
        SafeToRunReport.SafeToRunReportSuccess(osDetectionStrings.genericPassMessage())

    companion object {
        const val ERROR_CODE = "os-config-failure"
    }
}

package com.andro.safetorun.features.oscheck

import android.content.Context
import com.andro.safetorun.checks.SafeToRunCheck
import com.andro.safetorun.conditional.Conditional
import com.andro.safetorun.conditional.ConditionalResponse
import com.andro.safetorun.reporting.SafeToRunReport
import com.andro.safetorun.reporting.toMultipleReport

class OSDetectionCheck(
    private val osDetectionConfig: OSDetectionConfig,
    private val osDetectionStrings : OSDetectionStrings
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


fun Context.osDetectionCheck(vararg conditional: Conditional): SafeToRunCheck {
    return OSDetectionCheck(OSDetectionConfig(conditional.toList()), AndroidOSDetectionStrings(this))
}
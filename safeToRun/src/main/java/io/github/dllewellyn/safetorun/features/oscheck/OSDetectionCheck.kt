package io.github.dllewellyn.safetorun.features.oscheck

import android.content.Context
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.conditional.ConditionalResponse
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.github.dllewellyn.safetorun.reporting.toMultipleReport

internal class OSDetectionCheck(
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
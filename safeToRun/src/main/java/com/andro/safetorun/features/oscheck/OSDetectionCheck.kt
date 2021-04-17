package com.andro.safetorun.features.oscheck

import android.content.Context
import com.andro.safetorun.R
import com.andro.safetorun.checks.SafeToRunCheck
import com.andro.safetorun.conditional.Conditional
import com.andro.safetorun.conditional.ConditionalResponse
import com.andro.safetorun.reporting.SafeToRunReport
import com.andro.safetorun.reporting.toMultipleReport

class OSDetectionCheck(
    private val osDetectionConfig: OSDetectionConfig
) : SafeToRunCheck {

    override fun canRun(context: Context): SafeToRunReport {
        val results = osDetectionConfig.bannedOsResult
            .map { conditional -> conditional() }
            .filter { result -> result.failed }
            .map { result -> createSafeToRunReportFailure(result, context) }

        return if (results.isEmpty()) {
            createSafeToRunReportSuccess(context)
        } else {
            results.toList().toMultipleReport()
        }
    }

    private fun createSafeToRunReportFailure(
        result: ConditionalResponse,
        context: Context
    ): SafeToRunReport.SafeToRunReportFailure {
        return SafeToRunReport.SafeToRunReportFailure(
            ERROR_CODE,
            result.message ?: context.resources.getString(R.string.os_check_failed)
        )
    }

    private fun createSafeToRunReportSuccess(context: Context) =
        SafeToRunReport.SafeToRunReportSuccess(context.resources.getString(R.string.os_check_passed))

    companion object {
        const val ERROR_CODE = "os-config-failure"
    }
}


fun osDetectionCheck(vararg conditional: Conditional): SafeToRunCheck {
    return OSDetectionCheck(OSDetectionConfig(conditional.toList()))
}
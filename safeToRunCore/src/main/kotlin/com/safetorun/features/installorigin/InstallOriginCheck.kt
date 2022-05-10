package com.safetorun.features.installorigin

import com.safetorun.checks.SafeToRunCheck
import com.safetorun.reporting.SafeToRunReport

internal class InstallOriginCheck(
    private val installOriginStrings: InstallOriginStrings,
    private val allowedPackages: List<InstallOrigin>,
    private val installOriginQuery: InstallOriginQuery
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        return installOriginQuery.getInstallPackageName()?.let { installingPackage ->
            if (allowedPackages.map { it.originPackage }.contains(installingPackage)) {
                SafeToRunReport.SafeToRunReportSuccess(installOriginStrings.packageWasInAllowedList())
            } else {
                SafeToRunReport.SafeToRunReportFailure(
                    FAILURE_REASON_NOT_IN_LIST,
                    installOriginStrings.packageWasNotInAllowedList(installingPackage)
                )
            }
        } ?: SafeToRunReport.SafeToRunReportFailure(FAILURE_REASON, installOriginStrings.couldNotFindPackage())
    }

    companion object {
        const val FAILURE_REASON = "NF"
        const val FAILURE_REASON_NOT_IN_LIST = "NIL"
    }
}

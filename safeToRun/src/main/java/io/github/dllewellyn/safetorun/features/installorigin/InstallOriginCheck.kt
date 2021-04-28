package io.github.dllewellyn.safetorun.features.installorigin

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport


@SuppressLint("NewApi")
private fun Context.getInstaller(versionNumber: Int = Build.VERSION.SDK_INT): String? {
    return if (versionNumber >= Build.VERSION_CODES.R) {
        packageManager.getInstallSourceInfo(packageName).installingPackageName
    } else {
        packageManager.getInstallerPackageName(packageName)
    }
}

internal class InstallOriginCheck(
    private val context: Context,
    private val installOriginStrings: InstallOriginStrings,
    private val allowedPackages: List<InstallOrigin>,
    private val versionNumber: Int = Build.VERSION.SDK_INT,
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        return context.getInstaller(versionNumber)?.let { installingPackage ->
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
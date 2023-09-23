package com.safetorun.plus.queries

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import com.safetorun.features.oscheck.OSInformationQuery

/**
 * Get the installer that installed this application
 */
@SuppressLint("NewApi")
fun Context.getInstaller(osInformationQuery: OSInformationQuery = osInformationQuery()): String {
    return if (osInformationQuery.osVersion()  >= Build.VERSION_CODES.R) {
        packageManager.getInstallSourceInfo(packageName).installingPackageName
    } else {
        packageManager.getInstallerPackageName(packageName)
    } ?: "Not found"
}

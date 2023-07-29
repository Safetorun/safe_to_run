package com.safetorun.plus.queries

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build

/**
 * Get the installer that installed this application
 *
 * @param versionNumber the version number that we're running on
 */
@SuppressLint("NewApi")
fun Context.getInstaller(versionNumber: Int = Build.VERSION.SDK_INT): String {
    return if (versionNumber >= Build.VERSION_CODES.R) {
        packageManager.getInstallSourceInfo(packageName).installingPackageName
    } else {
        packageManager.getInstallerPackageName(packageName)
    } ?: "Not found"
}

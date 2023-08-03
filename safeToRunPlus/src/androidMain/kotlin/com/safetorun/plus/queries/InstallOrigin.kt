package com.safetorun.plus.queries

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build

/**
 * Get the installer that installed this application
 */
@SuppressLint("NewApi")
fun Context.getInstaller(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        packageManager.getInstallSourceInfo(packageName).installingPackageName
    } else {
        packageManager.getInstallerPackageName(packageName)
    } ?: "Not found"
}

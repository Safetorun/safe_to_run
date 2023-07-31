package com.safetorun.plus.queries

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build


@Suppress("DEPRECATION")
@SuppressLint("QueryPermissionsNeeded")
internal fun Context.listInstalledPackages(): List<String> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getInstalledPackages(PackageManager.PackageInfoFlags.of(0))
            .map { it.packageName }
    } else {
        packageManager.getInstalledPackages(0)
            .map { it.packageName }
    }
}

package com.safetorun.features.blacklistedapps

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

@Suppress("DEPRECATION")
internal class AndroidInstalledPackagesQuery(private val context: Context) : InstalledPackagesQuery {
    @SuppressLint("QueryPermissionsNeeded")
    override fun listInstalledPackages(): List<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getInstalledPackages(PackageManager.PackageInfoFlags.of(0))
                .map { it.packageName }
        } else {
            context.packageManager.getInstalledPackages(0)
                .map { it.packageName }
        }
    }
}

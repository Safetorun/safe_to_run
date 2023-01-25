package com.safetorun.logger.metadata

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.safetorun.logger.models.AppMetadata

internal class MetadataBuilder constructor(private val context: Context) {

    /**
     * Build the app metadata
     */
    fun build() = packageInfo().run {
        AppMetadata(
            packageName = context.packageName,
            appVersion = versionName,
            versionCode = versionCode(),
            firstInstallTime = firstInstallTime,
            lastUpdateTime = lastUpdateTime
        )
    }

    @Suppress("DEPRECATION")
    private fun PackageInfo.versionCode() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        longVersionCode
    } else {
        versionCode.toLong()
    }

    @Suppress("DEPRECATION")
    private fun packageInfo() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.packageManager.getPackageInfo(
            context.packageName,
            PackageManager.PackageInfoFlags.of(0)
        )
    } else {
        context.packageManager.getPackageInfo(
            context.packageName,
            0
        )
    }
}

internal fun Context.metadata() = MetadataBuilder(this).build()

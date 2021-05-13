package io.github.dllewellyn.safetorun.features.installorigin

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build

internal class AndroidInstallOriginQuery(
    private val context: Context,
    private val versionNumber: Int = Build.VERSION.SDK_INT,
) : InstallOriginQuery {

    override fun getInstallPackageName(): String? {
        return context.getInstaller(versionNumber)
    }

    @SuppressLint("NewApi")
    private fun Context.getInstaller(versionNumber: Int = Build.VERSION.SDK_INT): String? {
        return if (versionNumber >= Build.VERSION_CODES.R) {
            packageManager.getInstallSourceInfo(packageName).installingPackageName
        } else {
            packageManager.getInstallerPackageName(packageName)
        }
    }
}

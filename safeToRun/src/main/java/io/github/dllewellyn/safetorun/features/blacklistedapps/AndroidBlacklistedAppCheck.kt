package io.github.dllewellyn.safetorun.features.blacklistedapps

import android.content.Context

internal class AndroidBlacklistedAppCheck(private val context: Context) :
    io.github.dllewellyn.safetorun.features.blacklistedapps.BlacklistedAppCheck {

    override fun isAppPresent(packageName: String): Boolean {
        // https://developer.android.com/training/package-visibility/declaring
        return context.packageManager.getInstalledPackages(0)
            .map { it.packageName }
            .contains(packageName)
    }
}


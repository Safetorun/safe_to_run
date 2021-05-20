package io.github.dllewellyn.safetorun.features.blacklistedapps

import android.content.Context

internal class AndroidBlacklistedAppCheck(
    private val context: Context,
    private val installedPackagesQuery: InstalledPackagesQuery = AndroidInstalledPackagesQuery(context)
) : BlacklistedAppCheck {

    override fun isAppPresent(packageName: String): Boolean {
        // https://developer.android.com/training/package-visibility/declaring
        return installedPackagesQuery.listInstalledPackages()
            .contains(packageName)
    }
}

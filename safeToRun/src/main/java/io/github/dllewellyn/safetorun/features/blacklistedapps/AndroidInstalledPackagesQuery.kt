package io.github.dllewellyn.safetorun.features.blacklistedapps

import android.content.Context

internal class AndroidInstalledPackagesQuery(private val context: Context) : InstalledPackagesQuery {
    override fun listInstalledPackages(): List<String> {
        return context.packageManager.getInstalledPackages(0)
            .map { it.packageName }
    }
}

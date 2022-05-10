package com.safetorun.features.blacklistedapps

internal interface InstalledPackagesQuery {
    fun listInstalledPackages(): List<String>
}

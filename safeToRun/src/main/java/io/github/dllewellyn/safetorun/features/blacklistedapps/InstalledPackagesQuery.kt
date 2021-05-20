package io.github.dllewellyn.safetorun.features.blacklistedapps

internal interface InstalledPackagesQuery {
    fun listInstalledPackages(): List<String>
}

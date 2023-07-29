package com.safetorun.plus.queries

internal interface InstalledPackagesQuery {
    fun listInstalledPackages(): List<String>
}

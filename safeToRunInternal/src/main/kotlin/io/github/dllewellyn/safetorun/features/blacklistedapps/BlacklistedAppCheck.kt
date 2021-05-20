package io.github.dllewellyn.safetorun.features.blacklistedapps

interface BlacklistedAppCheck {
    fun isAppPresent(packageName: String): Boolean
}

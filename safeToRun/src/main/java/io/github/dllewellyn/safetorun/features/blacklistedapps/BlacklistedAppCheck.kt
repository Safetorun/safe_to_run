package io.github.dllewellyn.safetorun.features.blacklistedapps

internal interface BlacklistedAppCheck {
    fun isAppPresent(packageName : String) : Boolean
}
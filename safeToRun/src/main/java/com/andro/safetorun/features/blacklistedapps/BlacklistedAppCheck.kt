package com.andro.safetorun.features.blacklistedapps

interface BlacklistedAppCheck {
    fun isAppPresent(packageName : String) : Boolean
}
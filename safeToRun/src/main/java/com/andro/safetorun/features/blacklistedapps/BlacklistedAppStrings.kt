package com.andro.safetorun.features.blacklistedapps

interface BlacklistedAppStrings {
    fun foundBlacklistedAppMessage(blacklistedApp: String): String
    fun didNotFindBlacklistedAppMessage(): String
}
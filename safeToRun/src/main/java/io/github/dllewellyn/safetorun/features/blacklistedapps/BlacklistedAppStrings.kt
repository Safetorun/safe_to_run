package io.github.dllewellyn.safetorun.features.blacklistedapps

internal interface BlacklistedAppStrings {
    fun foundBlacklistedAppMessage(blacklistedApp: String): String
    fun didNotFindBlacklistedAppMessage(): String
}
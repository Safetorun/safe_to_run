package com.safetorun.features.blacklistedapps

/**
 * Strings to use when running a blacklisted app check
 */
interface BlacklistedAppStrings {
    /**
     * String to use if a blacklisted app was found
     *
     * @param blacklistedApp name of the blacklisted application
     */
    fun foundBlacklistedAppMessage(blacklistedApp: String): String

    /**
     * Message to use if we did not find a blacklisted application
     */
    fun didNotFindBlacklistedAppMessage(): String
}

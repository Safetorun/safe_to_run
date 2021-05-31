package io.github.dllewellyn.safetorun.features.blacklistedapps

/**
 * Check for a blacklisted app installed on device
 */
interface BlacklistedAppCheck {

    /**
     * Check for a blacklisted app installed on device
     *
     * @param packageName package to see if it's installed
     *
     */
    fun isAppPresent(packageName: String): Boolean
}

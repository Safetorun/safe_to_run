package com.safetorun.backendresilience.builders

/**
 * Build a blacklisted app configuration
 */
interface BlacklistedAppConfigurationBuilder {
    /**
     * Add a blacklisted app
     */
    operator fun String.unaryPlus()

    /**
     * Add a blacklisted app
     */
    fun String.blacklistApp()
}

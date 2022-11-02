package com.safetorun.resilienceshared.builders

import com.safetorun.backendresilience.dto.BackendResilience
import com.safetorun.resilienceshared.dto.BlacklistedAppResilience

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

/**
 * Base class for blacklisted app configuration
 */
class BaseBlacklistedAppConfigurationBuilder internal constructor() : BlacklistedAppConfigurationBuilder {

    private val blacklistedApps = mutableListOf<String>()

    /**
     * Add a blacklisted app
     */
    override operator fun String.unaryPlus() {
        blacklistedApps.add(this)
    }

    /**
     * Add a blacklisted app
     */
    override fun String.blacklistApp() {
        +this
    }

    internal fun build() = BlacklistedAppResilience(blacklistedApps)

}

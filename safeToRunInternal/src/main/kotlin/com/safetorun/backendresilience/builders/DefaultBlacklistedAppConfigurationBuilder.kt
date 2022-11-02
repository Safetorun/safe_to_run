package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.BlacklistedAppConfigurationOffDevice
import com.safetorun.resilienceshared.dto.Severity

/**
 * Build a blacklisted app configuration
 */
class DefaultBlacklistedAppConfigurationBuilder internal constructor(private val severity: Severity) :
    BlacklistedAppConfigurationBuilder {
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

    internal fun build(): BlacklistedAppConfigurationOffDevice {
        return BlacklistedAppConfigurationOffDevice(blacklistedApps, severity)
    }
}

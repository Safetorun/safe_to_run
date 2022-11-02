package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.BlacklistedAppConfigurationOffDevice
import com.safetorun.resilienceshared.dto.Severity

/**
 * Build a blacklisted app configuration
 */
class BlacklistedAppConfigurationBuilder internal constructor(private val severity: Severity) {
    private val blacklistedApps = mutableListOf<String>()

    /**
     * Add a blacklisted app
     */
    operator fun String.unaryPlus() {
        blacklistedApps.add(this)
    }

    internal fun build(): BlacklistedAppConfigurationOffDevice {
        return BlacklistedAppConfigurationOffDevice(blacklistedApps, severity)
    }
}

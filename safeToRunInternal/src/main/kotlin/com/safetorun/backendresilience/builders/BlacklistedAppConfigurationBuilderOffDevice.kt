package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.BlacklistedAppConfigurationOffDevice
import com.safetorun.resilienceshared.builders.BaseBlacklistedAppConfigurationBuilder
import com.safetorun.resilienceshared.builders.BlacklistedAppConfigurationBuilder
import com.safetorun.resilienceshared.dto.Severity

/**
 * Build a blacklisted app configuration
 */
class BlacklistedAppConfigurationBuilderOffDevice internal constructor(
    private val severity: Severity,
    private val baseBuilder: BaseBlacklistedAppConfigurationBuilder = BaseBlacklistedAppConfigurationBuilder()
) : BlacklistedAppConfigurationBuilder by baseBuilder {

    internal fun build(): BlacklistedAppConfigurationOffDevice {
        return baseBuilder.build()
            .run { BlacklistedAppConfigurationOffDevice(blacklistedApps, severity) }
    }
}

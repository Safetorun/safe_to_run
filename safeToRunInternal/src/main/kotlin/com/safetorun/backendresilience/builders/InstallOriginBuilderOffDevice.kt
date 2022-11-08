package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.InstallOriginCheckOffDevice
import com.safetorun.resilienceshared.builders.BaseInstallOriginBuilder
import com.safetorun.resilienceshared.builders.InstallOriginBuilder
import com.safetorun.resilienceshared.dto.Severity

/**
 * Builder for install origin configuration
 */
class InstallOriginBuilderOffDevice internal constructor(
    private val severity: Severity,
    private val originCheckBuilder: BaseInstallOriginBuilder = BaseInstallOriginBuilder()
) : InstallOriginBuilder by originCheckBuilder {

    internal fun build(): InstallOriginCheckOffDevice {
        return originCheckBuilder.build()
            .run { InstallOriginCheckOffDevice(allowedInstallOrigins, severity) }
    }
}

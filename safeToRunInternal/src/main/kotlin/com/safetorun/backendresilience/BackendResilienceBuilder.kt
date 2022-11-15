package com.safetorun.backendresilience

import com.safetorun.backendresilience.dto.BackendResilienceDto
import com.safetorun.backendresilience.dto.BlacklistedAppConfigurationOffDevice
import com.safetorun.backendresilience.builders.BlacklistedAppConfigurationBuilderOffDevice
import com.safetorun.backendresilience.builders.InstallOriginBuilderOffDevice
import com.safetorun.backendresilience.dto.InstallOriginCheckOffDevice
import com.safetorun.backendresilience.dto.OSCheckConfigurationOffDevice
import com.safetorun.backendresilience.builders.OSCheckConfigurationBuilderOffDevice
import com.safetorun.resilienceshared.dto.Severity
import com.safetorun.backendresilience.dto.VerifySignatureConfigurationOffDevice
import com.safetorun.backendresilience.builders.VerifySignatureConfigurationOffDeviceBuilder

/**
 * Backend resilience builder
 */
class BackendResilienceBuilder internal constructor() {
    private val blacklistedAppCheck = mutableListOf<BlacklistedAppConfigurationOffDevice>()
    private val verifySignatureConfiguration = mutableListOf<VerifySignatureConfigurationOffDevice>()
    private val installOriginCheck = mutableListOf<InstallOriginCheckOffDevice>()
    private val osCheckConfiguration = mutableListOf<OSCheckConfigurationOffDevice>()

    /**
     * Add a signature verification
     *
     * @param severity severity of issue
     * @param configuration configuration for signature
     */
    fun verifySignature(severity: Severity, configuration: VerifySignatureConfigurationOffDeviceBuilder.() -> Unit) {
        verifySignatureConfiguration.add(
            VerifySignatureConfigurationOffDeviceBuilder(severity)
                .apply(configuration)
                .build()
        )
    }

    /**
     * Install origin check
     *
     * @param severity severity of issue
     * @param installOrigin configuration of install origin
     */
    fun installOriginCheck(severity: Severity, installOrigin: InstallOriginBuilderOffDevice.() -> Unit) {
        installOriginCheck.add(
            InstallOriginBuilderOffDevice(severity).apply(installOrigin).build()
        )
    }

    /**
     * Add a os check
     *
     * @param osCheckConf configuration for os check
     */
    fun oSCheck(osCheckConf: OSCheckConfigurationBuilderOffDevice.() -> Unit) {
        osCheckConfiguration.add(
            OSCheckConfigurationBuilderOffDevice().apply(osCheckConf).build()
        )
    }

    /**
     * Add a blacklisted app
     *
     * @param severity severity of issue
     * @param blacklistedApp configuration for blacklisted app
     */
    fun blacklistedApp(severity: Severity, blacklistedApp: BlacklistedAppConfigurationBuilderOffDevice.() -> Unit) {
        blacklistedAppCheck.add(
            BlacklistedAppConfigurationBuilderOffDevice(severity).apply(blacklistedApp).build()
        )
    }

    internal fun build() = BackendResilienceDto(
        blacklistedAppCheck,
        verifySignatureConfiguration,
        installOriginCheck,
        osCheckConfiguration
    )
}

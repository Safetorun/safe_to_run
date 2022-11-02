package com.safetorun.backendresilience

import com.safetorun.backendresilience.dto.BackendResilience
import com.safetorun.backendresilience.dto.BlacklistedAppConfigurationOffDevice
import com.safetorun.backendresilience.builders.BlacklistedAppConfigurationBuilder
import com.safetorun.backendresilience.builders.InstallOriginBuilder
import com.safetorun.backendresilience.dto.InstallOriginCheckOffDevice
import com.safetorun.backendresilience.dto.OSCheckConfigurationOffDevice
import com.safetorun.backendresilience.builders.OSCheckConfigurationBuilder
import com.safetorun.resilienceshared.dto.Severity
import com.safetorun.backendresilience.dto.VerifySignatureConfigurationOffDevice
import com.safetorun.backendresilience.builders.VerifySignatureConfigurationBuilder

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
    fun verifySignature(severity: Severity, configuration: VerifySignatureConfigurationBuilder.() -> Unit) {
        verifySignatureConfiguration.add(
            VerifySignatureConfigurationBuilder(severity)
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
    fun installOriginCheck(severity: Severity, installOrigin: InstallOriginBuilder.() -> Unit) {
        installOriginCheck.add(
            InstallOriginBuilder(severity).apply(installOrigin).build()
        )
    }

    /**
     * Add a os chec
     *
     * @param severity severity of issue
     * @param osCheckConf configuration for os check
     */
    fun oSCheck(osCheckConf: OSCheckConfigurationBuilder.() -> Unit) {
        osCheckConfiguration.add(
            OSCheckConfigurationBuilder().apply(osCheckConf).build()
        )
    }

    /**
     * Add a blacklisted app
     *
     * @param severity severity of issue
     * @param blacklistedApp configuration for blacklisted app
     */
    fun blacklistedApp(severity: Severity, blacklistedApp: BlacklistedAppConfigurationBuilder.() -> Unit) {
        blacklistedAppCheck.add(
            BlacklistedAppConfigurationBuilder(severity).apply(blacklistedApp).build()
        )
    }

    internal fun build() = BackendResilience(
        blacklistedAppCheck,
        verifySignatureConfiguration,
        installOriginCheck,
        osCheckConfiguration
    )
}

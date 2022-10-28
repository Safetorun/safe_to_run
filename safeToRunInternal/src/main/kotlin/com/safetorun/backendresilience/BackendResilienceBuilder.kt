package com.safetorun.backendresilience

import com.safetorun.backendresilience.dto.BackendResilience
import com.safetorun.backendresilience.dto.BlacklistedAppConfiguration
import com.safetorun.backendresilience.dto.BlacklistedAppConfigurationBuilder
import com.safetorun.backendresilience.dto.InstallOriginBuilder
import com.safetorun.backendresilience.dto.InstallOriginCheck
import com.safetorun.backendresilience.dto.OSCheckConfiguration
import com.safetorun.backendresilience.dto.OSCheckConfigurationBuilder
import com.safetorun.backendresilience.dto.Severity
import com.safetorun.backendresilience.dto.VerifySignatureConfiguration
import com.safetorun.backendresilience.dto.VerifySignatureConfigurationBuilder

/**
 * Backend resilience builder
 */
class BackendResilienceBuilder internal constructor() {
    private val blacklistedAppCheck = mutableListOf<BlacklistedAppConfiguration>()
    private val verifySignatureConfiguration = mutableListOf<VerifySignatureConfiguration>()
    private val installOriginCheck = mutableListOf<InstallOriginCheck>()
    private val osCheckConfiguration = mutableListOf<OSCheckConfiguration>()

    fun verifySignature(severity: Severity, configuration: VerifySignatureConfigurationBuilder.() -> Unit) {
        verifySignatureConfiguration.add(
            VerifySignatureConfigurationBuilder(severity)
                .apply(configuration)
                .build()
        )
    }

    fun installOriginCheck(severity: Severity, installOrigin: InstallOriginBuilder.() -> Unit) {
        installOriginCheck.add(
            InstallOriginBuilder(severity).apply(installOrigin).build()
        )
    }

    fun oSCheck(osCheckConf: OSCheckConfigurationBuilder.() -> Unit) {
        osCheckConfiguration.add(
            OSCheckConfigurationBuilder().apply(osCheckConf).build()
        )
    }

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
package com.safetorun.resilienceshared

import com.safetorun.resilienceshared.builders.BaseBlacklistedAppConfigurationBuilder
import com.safetorun.resilienceshared.builders.BaseInstallOriginBuilder
import com.safetorun.resilienceshared.builders.BaseVerifySignatureConfigurationBuilder
import com.safetorun.resilienceshared.builders.OSCheckConfigurationBuilder
import com.safetorun.resilienceshared.dto.BlacklistedAppConfiguration
import com.safetorun.resilienceshared.dto.InstallOriginConfiguration
import com.safetorun.resilienceshared.dto.OSCheckConfiguration
import com.safetorun.resilienceshared.dto.OnDeviceResilience
import com.safetorun.resilienceshared.dto.VerifySignatureConfiguration

/**
 * Backend resilience builder
 */
class OnDeviceResilienceBuilder internal constructor() {
    private var blacklistedAppCheck = BlacklistedAppConfiguration()
    private var verifySignatureConfiguration = VerifySignatureConfiguration()
    private var installOriginCheck = InstallOriginConfiguration()
    private var osCheckConfiguration = OSCheckConfiguration()

    /**
     * Add a signature verification
     *
     * @param configuration configuration for signature
     */
    fun verifySignature(configuration: BaseVerifySignatureConfigurationBuilder.() -> Unit) {
        verifySignatureConfiguration =
            BaseVerifySignatureConfigurationBuilder()
                .apply(configuration)
                .build()
    }

    /**
     * Install origin check
     *
     * @param installOrigin configuration of install origin
     */
    fun installOriginCheck(installOrigin: BaseInstallOriginBuilder.() -> Unit) {
        installOriginCheck = BaseInstallOriginBuilder().apply(installOrigin).build()
    }

    /**
     * Add a os check
     *
     * @param osCheckConf configuration for os check
     */
    fun oSCheck(osCheckConf: OSCheckConfigurationBuilder.() -> Unit) {
        osCheckConfiguration = OSCheckConfigurationBuilder().apply(osCheckConf).build()
    }

    /**
     * Add a blacklisted app
     *
     * @param blacklistedApp configuration for blacklisted app
     */
    fun blacklistedApp(
        blacklistedApp: BaseBlacklistedAppConfigurationBuilder.() -> Unit
    ) {
        blacklistedAppCheck =
            BaseBlacklistedAppConfigurationBuilder().apply(blacklistedApp).build()
    }

    internal fun build() = OnDeviceResilience(
        blacklistedAppCheck.blacklistedApps,
        verifySignatureConfiguration.allowedSignatures,
        installOriginCheck.allowedInstallOrigins,
        osCheckConfiguration.configuration
    )
}
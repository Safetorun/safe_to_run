package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.VerifySignatureConfigurationOffDevice
import com.safetorun.resilienceshared.builders.BaseVerifySignatureConfigurationBuilder
import com.safetorun.resilienceshared.builders.VerifySignatureConfigurationBuilder
import com.safetorun.resilienceshared.dto.Severity

/**
 * Verify signature configuration
 */
class VerifySignatureConfigurationOffDeviceBuilder internal constructor(
    private val severity: Severity,
    private val configurationBuilder: BaseVerifySignatureConfigurationBuilder
    = BaseVerifySignatureConfigurationBuilder()
) : VerifySignatureConfigurationBuilder by configurationBuilder {

    internal fun build(): VerifySignatureConfigurationOffDevice {
        return configurationBuilder.build()
            .run { VerifySignatureConfigurationOffDevice(allowedSignatures, severity) }
    }
}

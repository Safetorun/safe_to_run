package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.VerifySignatureConfigurationOffDevice
import com.safetorun.resilienceshared.dto.Severity

/**
 * Verify signature configuration
 */
class VerifySignatureConfigurationBuilder internal constructor(private val severity: Severity) {
    private val allowedSignatures = mutableListOf<String>()

    /**
     * Add allowed signature
     */
    operator fun String.unaryPlus() {
        allowedSignatures.add(this)
    }

    /**
     * Add allowed signature
     */
    fun String.allowSignature() {
        allowedSignatures.add(this)
    }

    internal fun build(): VerifySignatureConfigurationOffDevice {
        return VerifySignatureConfigurationOffDevice(allowedSignatures, severity)
    }
}

package com.safetorun.backendresilience.dto

import com.safetorun.resilienceshared.dto.Severity

/**
 * Verify signature configuration
 */
@kotlinx.serialization.Serializable
data class VerifySignatureConfigurationOffDevice(
    var allowedSignatures: List<String> = emptyList(),
    var severity: Severity = Severity.None
)

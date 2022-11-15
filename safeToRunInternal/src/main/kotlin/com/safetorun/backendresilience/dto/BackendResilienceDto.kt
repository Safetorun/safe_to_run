package com.safetorun.backendresilience.dto

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class BackendResilienceDto(
    val blacklistedAppCheck: List<BlacklistedAppConfigurationOffDevice> = emptyList(),
    val verifySignatureConfiguration: List<VerifySignatureConfigurationOffDevice> = emptyList(),
    val installOriginCheck: List<InstallOriginCheckOffDevice> = emptyList(),
    val osCheckConfiguration: List<OSCheckConfigurationOffDevice> = emptyList()
)

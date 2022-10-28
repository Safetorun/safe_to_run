package com.safetorun.backendresilience.dto

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class BackendResilience(
    val blacklistedAppCheck: List<BlacklistedAppConfiguration> = emptyList(),
    val verifySignatureConfiguration: List<VerifySignatureConfiguration> = emptyList(),
    val installOriginCheck: List<InstallOriginCheck> = emptyList(),
    val osCheckConfiguration: List<OSCheckConfiguration> = emptyList()
)


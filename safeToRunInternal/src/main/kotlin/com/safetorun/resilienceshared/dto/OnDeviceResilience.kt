package com.safetorun.resilienceshared.dto

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class OnDeviceResilience(
    val blacklistedApps: List<String> = emptyList(),
    val allowedSignatures: List<String> = emptyList(),
    val installOriginCheck: List<String> = emptyList(),
    val osCheckConfiguration: List<SingleOSCheckConfiguration> = emptyList()
)

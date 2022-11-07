package com.safetorun.resilienceshared.dto

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class OnDeviceResilience(
    val blacklistedApps: List<String>? = null,
    val allowedSignatures: List<String>? = null,
    val installOriginCheck: List<String>? = null,
    val osCheckConfiguration: List<SingleOSCheckConfiguration>? = null,
    val rootCheck: RootCheckConfiguration? = null
)

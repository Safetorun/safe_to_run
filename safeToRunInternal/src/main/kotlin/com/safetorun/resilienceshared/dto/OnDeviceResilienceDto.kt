package com.safetorun.resilienceshared.dto

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class OnDeviceResilienceDto(
    var blacklistedApps: List<String>? = null,
    var allowedSignatures: List<String>? = null,
    var installOriginCheck: List<String>? = null,
    var osCheckConfiguration: List<SingleOSCheckConfiguration>? = null,
    var rootCheck: RootCheckConfiguration? = null,
    var banDebugger: Boolean = false
)

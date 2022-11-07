package com.safetorun.resilienceshared.dto

/**
 * Base blacklisted app resilience check
 */
@kotlinx.serialization.Serializable
data class BlacklistedAppConfiguration(
    val blacklistedApps: List<String> = emptyList()
)

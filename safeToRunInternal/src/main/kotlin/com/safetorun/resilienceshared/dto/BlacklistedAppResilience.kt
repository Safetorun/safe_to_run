package com.safetorun.resilienceshared.dto

/**
 * Base blacklisted app resilience check
 */
@kotlinx.serialization.Serializable
data class BlacklistedAppResilience(
    val blacklistedApps: List<String> = emptyList()
)

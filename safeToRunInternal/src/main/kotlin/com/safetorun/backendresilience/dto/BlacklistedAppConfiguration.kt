package com.safetorun.backendresilience.dto

/**
 * Blacklisted app configuration
 */
@kotlinx.serialization.Serializable
data class BlacklistedAppConfiguration(
    var blacklistedApps: List<String> = emptyList(),
    var severity: Severity = Severity.None
)

class BlacklistedAppConfigurationBuilder internal constructor(private val severity: Severity) {
    private val blacklistedApps = mutableListOf<String>()

    operator fun String.unaryPlus() {
        blacklistedApps.add(this)
    }

    internal fun build(): BlacklistedAppConfiguration {
        return BlacklistedAppConfiguration(blacklistedApps, severity)
    }
}
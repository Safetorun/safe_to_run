package com.safetorun.backendresilience.dto

/**
 * Blacklisted app configuration
 */
@kotlinx.serialization.Serializable
data class BlacklistedAppConfiguration(
    var blacklistedApps: List<String> = emptyList(),
    var severity: Severity = Severity.None
)

/**
 * Build a blacklisted app configuration
 */
class BlacklistedAppConfigurationBuilder internal constructor(private val severity: Severity) {
    private val blacklistedApps = mutableListOf<String>()

    /**
     * Add a blacklisted app
     */
    operator fun String.unaryPlus() {
        blacklistedApps.add(this)
    }

    /**
     * Add a blacklisted app
     */
    fun String.blacklistApp() {
        +this
    }

    internal fun build(): BlacklistedAppConfiguration {
        return BlacklistedAppConfiguration(blacklistedApps, severity)
    }
}

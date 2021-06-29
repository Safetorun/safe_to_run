package io.github.dllewellyn.safetorun.backend.configuration

/**
 * Blacklisted app configuration
 */
data class BlacklistedAppConfigurationDto(val blacklistedApps: List<String>, val severity: Severity)

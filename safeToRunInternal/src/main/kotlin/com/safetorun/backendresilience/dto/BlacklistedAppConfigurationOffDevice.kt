package com.safetorun.backendresilience.dto

import com.safetorun.resilienceshared.dto.Severity

/**
 * Blacklisted app configuration
 */
@kotlinx.serialization.Serializable
data class BlacklistedAppConfigurationOffDevice(
    var blacklistedApps: List<String> = emptyList(),
    var severity: Severity = Severity.None
)


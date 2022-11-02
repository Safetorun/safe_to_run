package com.safetorun.backendresilience.dto

import com.safetorun.resilienceshared.dto.Severity
import com.safetorun.resilienceshared.dto.SingleCheck

/**
 * Os check configuration
 */
@kotlinx.serialization.Serializable
data class OSCheckConfigurationOffDevice(var configuration: List<SingleOSCheckConfigurationOffDevice> = emptyList())

/**
 * OS Check configuration
 */
@kotlinx.serialization.Serializable
data class SingleOSCheckConfigurationOffDevice(
    var allChecks: List<SingleCheck> = emptyList(),
    var severity: Severity = Severity.None,
)

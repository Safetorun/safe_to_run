package com.safetorun.backendresilience.dto

import com.safetorun.resilienceshared.dto.Severity
import com.safetorun.resilienceshared.dto.SingleIntCheck
import com.safetorun.resilienceshared.dto.SingleStringCheck

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
    var allStringChecks: List<SingleStringCheck> = emptyList(),
    var allIntChecks: List<SingleIntCheck> = emptyList(),
    var severity: Severity = Severity.None,
)

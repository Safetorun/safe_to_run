package com.safetorun.backendresilience.dto

import com.safetorun.resilienceshared.dto.Severity

/**
 * Install origin check
 */
@kotlinx.serialization.Serializable
data class InstallOriginCheckOffDevice(
    var allowedInstallOrigins: List<String> = emptyList(),
    var severity: Severity = Severity.None
)


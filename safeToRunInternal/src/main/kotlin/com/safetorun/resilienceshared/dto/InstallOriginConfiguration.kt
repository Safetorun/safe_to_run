package com.safetorun.resilienceshared.dto

/**
 * Adding missing comment
 */
@kotlinx.serialization.Serializable
data class InstallOriginConfiguration(
    var allowedInstallOrigins: List<String> = emptyList()
)

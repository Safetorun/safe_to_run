package com.safetorun.resilienceshared.dto

/**
 * Adding missing comment
 */
@kotlinx.serialization.Serializable
data class InstallOriginCheck(
    var allowedInstallOrigins: List<String> = emptyList()
)

package com.safetorun.resilienceshared.dto

@kotlinx.serialization.Serializable
data class InstallOriginCheck(
    var allowedInstallOrigins: List<String> = emptyList()
)

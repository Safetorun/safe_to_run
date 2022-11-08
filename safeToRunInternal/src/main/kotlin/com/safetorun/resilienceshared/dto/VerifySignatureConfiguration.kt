package com.safetorun.resilienceshared.dto

/**
 * Verify signature configuration
 */
@kotlinx.serialization.Serializable
data class VerifySignatureConfiguration(
    var allowedSignatures: List<String> = emptyList()
)

package io.github.dllewellyn.safetorun.backend.configuration

/**
 * Verify signature configuration
 */
data class VerifySignatureConfigurationDto(val allowedSignatures : List<String>, val severity: Severity)

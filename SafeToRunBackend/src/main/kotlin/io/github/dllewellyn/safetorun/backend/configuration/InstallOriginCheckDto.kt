package io.github.dllewellyn.safetorun.backend.configuration

/**
 * Install origin check
 */
data class InstallOriginCheckDto(val allowedInstallOrigins: List<String>, val severity: Severity)

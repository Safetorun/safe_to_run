package com.safetorun.backendresilience.dto

/**
 * Install origin check
 */
@kotlinx.serialization.Serializable
data class InstallOriginCheck(
    var allowedInstallOrigins: List<String> = emptyList(),
    var severity: Severity = Severity.None
)

class InstallOriginBuilder internal constructor(private val severity: Severity) {
    private val allowedInstallOriginCheck = mutableListOf<String>()

    operator fun String.unaryPlus() {
        allowedInstallOriginCheck.add(this)
    }

    internal fun build(): InstallOriginCheck {
        return InstallOriginCheck(allowedInstallOriginCheck, severity)
    }
}

package com.safetorun.backendresilience.dto

/**
 * Verify signature configuration
 */
@kotlinx.serialization.Serializable
data class VerifySignatureConfiguration(
    var allowedSignatures: List<String> = emptyList(),
    var severity: Severity = Severity.None
)


class VerifySignatureConfigurationBuilder internal constructor(private val severity: Severity) {
    private val allowedSignatures = mutableListOf<String>()

    operator fun String.unaryPlus() {
        allowedSignatures.add(this)
    }

    internal fun build(): VerifySignatureConfiguration {
        return VerifySignatureConfiguration(allowedSignatures, severity)
    }
}

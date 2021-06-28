package io.github.dllewellyn.safetorun.backend.configuration

/**
 * Safe to run configuration
 */
data class SafeToRunConfigurationDto(
    val blacklistedAppCheck: BlacklistedAppConfigurationDto,
    val verifySignatureConfiguration: VerifySignatureConfigurationDto,
    val installOriginCheck: InstallOriginCheckDto
) {
    companion object {
        fun empty() = SafeToRunConfigurationDto(
            BlacklistedAppConfigurationDto(emptyList(), Severity.None),
            VerifySignatureConfigurationDto(emptyList(), Severity.None),
            InstallOriginCheckDto(emptyList(), Severity.None)
        )
    }
}

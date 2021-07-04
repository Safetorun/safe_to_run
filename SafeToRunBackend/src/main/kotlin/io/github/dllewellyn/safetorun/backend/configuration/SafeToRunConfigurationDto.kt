package io.github.dllewellyn.safetorun.backend.configuration

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument

/**
 * Safe to run configuration
 */
@DynamoDBDocument
internal data class SafeToRunConfigurationDto(
    val blacklistedAppCheck: BlacklistedAppConfigurationDto,
    val verifySignatureConfiguration: VerifySignatureConfigurationDto,
    val installOriginCheck: InstallOriginCheckDto,
    val osCheckConfiguration: OSCheckConfigurationDto
) {
    companion object {
        /**
         * Create an empty configuration
         */
        fun empty() = SafeToRunConfigurationDto(
            BlacklistedAppConfigurationDto(emptyList(), Severity.None),
            VerifySignatureConfigurationDto(emptyList(), Severity.None),
            InstallOriginCheckDto(emptyList(), Severity.None),
            OSCheckConfigurationDto(emptyList(), Severity.None)
        )
    }
}

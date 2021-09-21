package io.github.dllewellyn.safetorun.backend.configuration

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument

/**
 * Safe to run configuration
 */
@DynamoDBDocument
data class SafeToRunConfigurationDto(
    var blacklistedAppCheck: BlacklistedAppConfigurationDto = BlacklistedAppConfigurationDto(
        emptyList(),
        Severity.None
    ),
    var verifySignatureConfiguration: VerifySignatureConfigurationDto = VerifySignatureConfigurationDto(
        emptyList(),
        Severity.None
    ),
    var installOriginCheck: InstallOriginCheckDto = InstallOriginCheckDto(emptyList(), Severity.None),
    var osCheckConfiguration: OSCheckConfigurationDto = OSCheckConfigurationDto(emptyList(), Severity.None)
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

package io.github.dllewellyn.safetorun.backend.configuration

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum

/**
 * Verify signature configuration
 */
@DynamoDBDocument
data class VerifySignatureConfigurationDto(
    var allowedSignatures: List<String>,
    @DynamoDBTypeConvertedEnum
    var severity: Severity
)

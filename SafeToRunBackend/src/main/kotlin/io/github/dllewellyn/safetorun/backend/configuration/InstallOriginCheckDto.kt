package io.github.dllewellyn.safetorun.backend.configuration

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum

/**
 * Install origin check
 */
@DynamoDBDocument
data class InstallOriginCheckDto(
    val allowedInstallOrigins: List<String>,
    @DynamoDBTypeConvertedEnum val severity: Severity
)

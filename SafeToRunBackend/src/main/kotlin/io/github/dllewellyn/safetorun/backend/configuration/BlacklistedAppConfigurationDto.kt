package io.github.dllewellyn.safetorun.backend.configuration

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum

/**
 * Blacklisted app configuration
 */
@DynamoDBDocument
data class BlacklistedAppConfigurationDto(var blacklistedApps: List<String>,
                                          @DynamoDBTypeConvertedEnum
                                          var severity: Severity)

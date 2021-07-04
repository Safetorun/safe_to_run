package io.github.dllewellyn.safetorun.backend.configuration

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum

internal enum class CheckType {
    MinOsCheck,
    BannedBoard,
    BannedBootloader,
    BannedCpuAbi,
    BannedDevice,
    BannedHardware,
    BannedHost,
    BannedModel
}

@DynamoDBDocument
internal data class SingleCheck(
    val intValue: Int? = null,
    val stringValue: String? = null,
    @DynamoDBTypeConvertedEnum val checkType: CheckType
)

/**
 * OS Check configuration
 */
@DynamoDBDocument
internal data class OSCheckConfigurationDto(
    val allChecks: List<SingleCheck>,
    @DynamoDBTypeConvertedEnum val severity: Severity
)

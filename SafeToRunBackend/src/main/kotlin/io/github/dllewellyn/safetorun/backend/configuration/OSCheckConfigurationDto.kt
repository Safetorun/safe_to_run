package io.github.dllewellyn.safetorun.backend.configuration

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum

enum class CheckType {
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
data class SingleCheck(
    var intValue: Int? = null,
    var stringValue: String? = null,
    @DynamoDBTypeConvertedEnum var checkType: CheckType
)

/**
 * OS Check configuration
 */
@DynamoDBDocument
data class OSCheckConfigurationDto(
    var allChecks: List<SingleCheck>,
    @DynamoDBTypeConvertedEnum var severity: Severity
)

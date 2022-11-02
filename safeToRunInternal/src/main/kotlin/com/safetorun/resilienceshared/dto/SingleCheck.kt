package com.safetorun.resilienceshared.dto

import com.safetorun.resilienceshared.dto.CheckType

@kotlinx.serialization.Serializable
/**
 * Single os check
 */
data class SingleCheck(
    var intValue: Int? = null,
    var stringValue: String? = null,
    var checkType: CheckType = CheckType.BannedBoard,
    var checkUuid: String = ""
)

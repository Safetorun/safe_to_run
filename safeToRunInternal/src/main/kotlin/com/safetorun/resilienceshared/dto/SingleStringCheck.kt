package com.safetorun.resilienceshared.dto

import java.util.UUID

@kotlinx.serialization.Serializable
/**
 * Single os check
 */
data class SingleStringCheck(
    var stringValue: String = "",
    var checkType: StringCheckType = StringCheckType.BannedBoard,
    var checkUuid: String = UUID.randomUUID().toString(),
    var comparator: CheckComparator = CheckComparator.EQUALS,
)

@kotlinx.serialization.Serializable
/**
 * Single os check
 */
data class SingleIntCheck(
    var intValue: Int = -1,
    var comparator: CheckComparator = CheckComparator.EQUALS,
    var checkType: IntCheckType = IntCheckType.MinOsCheck,
    var checkUuid: String = UUID.randomUUID().toString()
)




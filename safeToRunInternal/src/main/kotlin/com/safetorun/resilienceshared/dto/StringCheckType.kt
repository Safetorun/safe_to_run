package com.safetorun.resilienceshared.dto

/**
 * Types of check
 */
enum class StringCheckType {
    BannedBoard,
    BannedBootloader,
    BannedCpuAbi,
    BannedDevice,
    BannedHardware,
    BannedHost,
    BannedModel
}

/**
 * Types of int checks
 */
enum class IntCheckType {
    MinOsCheck
}

enum class CheckComparator {
    EQUALS,
    GREATER_THAN
}

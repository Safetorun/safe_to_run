package com.safetorun.resilienceshared.dto

/**
 * Types of check
 */
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

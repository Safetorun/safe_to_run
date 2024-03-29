package com.safetorun.features.oscheck.emulator

import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.OsCheckConstants
import com.safetorun.features.oscheck.OsCheckConstants.XIAOMI
 import com.safetorun.features.oscheck.osInformationQuery

/**
 * Return true if we're running on an emulator
 *
 * @return true if we're running on avd
 */
inline fun banAvdEmulatorCheck(osInformationQuery: OSInformationQuery = osInformationQuery()) =
    isXiaomi(osInformationQuery).not() && (osInformationQuery.bootloader() == OsCheckConstants.UNKNOWN ||
            osInformationQuery.device() == OsCheckConstants.AVD_DEVICE_TYPE ||
            osInformationQuery.board() == OsCheckConstants.AVD_EMULATOR_BOARD)

/**
 * Ban bluestacks
 *
 * @return true if we're running on bluestacks
 */
inline fun banBluestacksEmulatorCheck(osInformationQuery: OSInformationQuery = osInformationQuery()) =
    isXiaomi(osInformationQuery).not() && osInformationQuery.bootloader() == OsCheckConstants.UNKNOWN

/**
 * Ban the genymotion emulator
 *
 * @return true if we're running on the genymotion emulator
 */
inline fun banGenymotionEmulatorCheck(osInformationQuery: OSInformationQuery = osInformationQuery()) =
    isXiaomi(osInformationQuery).not() && (osInformationQuery.board() == OsCheckConstants.UNKNOWN ||
            osInformationQuery.manufacturer() == OsCheckConstants.GENYMOTION_MANUFACTURER ||
            osInformationQuery.bootloader() == OsCheckConstants.UNKNOWN)

/**
 * Returns true if the device is a Xiaomi device
 *
 * @return true if we're on a Xiaomi device
 */
inline fun isXiaomi(osInformationQuery: OSInformationQuery = osInformationQuery()) =
    osInformationQuery.manufacturer() == XIAOMI

package com.safetorun.features.oscheck.emulator

import android.os.Build
import com.safetorun.features.oscheck.OsCheckConstants
import com.safetorun.features.oscheck.OsCheckConstants.XIAOMI

/**
 * Return true if we're running on an emulator
 *
 * @return true if we're running on avd
 */
inline fun banAvdEmulatorCheck() =
    isXiaomi().not() && (Build.BOOTLOADER == OsCheckConstants.UNKNOWN ||
            Build.DEVICE == OsCheckConstants.AVD_DEVICE_TYPE ||
            Build.BOARD == OsCheckConstants.AVD_EMULATOR_BOARD)

/**
 * Ban bluestacks
 *
 * @return true if we're running on bluestacks
 */
inline fun banBluestacksEmulatorCheck() =
    isXiaomi().not() && Build.BOOTLOADER == OsCheckConstants.UNKNOWN

/**
 * Ban the genymotion emulator
 *
 * @return true if we're running on the genymotion emulator
 */
inline fun banGenymotionEmulatorCheck() =
    isXiaomi().not() && (Build.BOARD == OsCheckConstants.UNKNOWN ||
            Build.MANUFACTURER == OsCheckConstants.GENYMOTION_MANUFACTURER ||
            Build.BOOTLOADER == OsCheckConstants.UNKNOWN)

/**
 * Returns true if the device is a Xiaomi device
 *
 * @return true if we're on a Xiaomi device
 */
inline fun isXiaomi() = Build.MANUFACTURER == XIAOMI

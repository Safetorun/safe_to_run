package io.github.dllewellyn.safetorun.features.oscheck.emulator

import android.os.Build
import io.github.dllewellyn.safetorun.features.oscheck.OsCheckConstants

/**
 * Return true if we're running on an emulator
 *
 * @return true if we're running on avd
 */
inline fun banAvdEmulatorCheck() =
    Build.BOOTLOADER == OsCheckConstants.UNKNOWN ||
            Build.DEVICE == OsCheckConstants.AVD_DEVICE_TYPE ||
            Build.BOARD == OsCheckConstants.AVD_EMULATOR_BOARD

/**
 * Ban bluestacks
 *
 * @return true if we're running on bluestacks
 */
inline fun banBluestacksEmulatorCheck() =
    Build.BOOTLOADER == OsCheckConstants.UNKNOWN

/**
 * Ben the genymotion emulator
 *
 * @return true if we're runing on the genymotion emulator
 */
inline fun banGenymotionEmulatorCheck() =
    Build.BOARD == OsCheckConstants.UNKNOWN ||
            Build.MANUFACTURER == OsCheckConstants.GENYMOTION_MANUFACTURER ||
            Build.BOOTLOADER == OsCheckConstants.UNKNOWN

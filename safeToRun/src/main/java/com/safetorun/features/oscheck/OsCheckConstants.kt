package com.safetorun.features.oscheck

/**
 * Constants file for OS Check providing static strings that are used
 * when doing OS Checks - primarily these are checks for emulator detection
 */
object OsCheckConstants {
    /** Type of board used on emulators */
    const val AVD_EMULATOR_BOARD = "goldfish_x86"

    /** Unknown can be used for bootloaders, boards etc on emulators */
    const val UNKNOWN = "unknown"

    /** Type of device for AVD emulator */
    const val AVD_DEVICE_TYPE = "generic_x86_arm"

    /** Type of device for genymotion */
    const val GENYMOTION_MANUFACTURER = "Genymotion"

    /** If the manufacturer is Xiaomi */
    const val XIAOMI = "Xiaomi"
}

package io.github.dllewellyn.safetorun.features.oscheck

object OsCheckConstants {
    /** Type of board used on emulators */
    const val AVD_EMULATOR_BOARD = "goldfish_x86"

    /** Unknown can be used for bootloaders, boards etc on emulators */
    const val UNKNOWN = "unknown"

    /** Type of device for AVD emulator */
    const val AVD_DEVICE_TYPE = "generic_x86_arm"

    /** Type of device for genymotion */
    const val GENYMOTION_MANUFACTURER = "Genymotion"
}

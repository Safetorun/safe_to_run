package com.safetorun.features.oscheck.emulators

import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.OsCheckConstants
import com.safetorun.features.oscheck.builders.bannedBoard
import com.safetorun.features.oscheck.builders.bannedBootloader
import com.safetorun.features.oscheck.builders.bannedDevice

/**
 * Ban the default android emulator from running
 */
fun OSInformationQuery.banAvdEmulator() =
    conditionalBuilder {
        with(bannedBootloader(OsCheckConstants.UNKNOWN))
        and(bannedDevice(OsCheckConstants.AVD_DEVICE_TYPE))
        and(bannedBoard(OsCheckConstants.AVD_EMULATOR_BOARD))
    }

package com.safetorun.features.oscheck.emulators

import com.safetorun.conditional.conditionalBuilder
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.OsCheckConstants
import com.safetorun.features.oscheck.builders.bannedBootloader

/**
 * Ban the bluestacks emulator from running
 */
fun OSInformationQuery.banBluestacksEmulator() =
    conditionalBuilder {
        with(bannedBootloader(OsCheckConstants.UNKNOWN))
    }

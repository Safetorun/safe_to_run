package io.github.dllewellyn.safetorun.features.oscheck.emulators

import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.OsCheckConstants
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedBootloader

/**
 * Ban the bluestacks emulator from running
 */
fun OSInformationQuery.banBluestacksEmulator() =
    conditionalBuilder {
        with(bannedBootloader(OsCheckConstants.UNKNOWN))
    }

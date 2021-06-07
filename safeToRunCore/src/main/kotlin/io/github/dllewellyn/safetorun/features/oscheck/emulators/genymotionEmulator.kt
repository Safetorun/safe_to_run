package io.github.dllewellyn.safetorun.features.oscheck.emulators

import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.OsCheckConstants
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedBoard
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedBootloader
import io.github.dllewellyn.safetorun.features.oscheck.conditionals.notManufacturer

/**
 * Ban the genymotion emulator from running
 */
fun OSInformationQuery.banGenymotionEmulator() =
    conditionalBuilder {
        with(bannedBoard(OsCheckConstants.UNKNOWN))
        and(notManufacturer(OsCheckConstants.GENYMOTION_MANUFACTURER))
        and(bannedBootloader(OsCheckConstants.UNKNOWN))
    }

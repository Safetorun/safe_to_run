package com.safetorun.features.oscheck.emulators

import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.OsCheckConstants
import com.safetorun.features.oscheck.builders.bannedBoard
import com.safetorun.features.oscheck.builders.bannedBootloader
import com.safetorun.features.oscheck.conditionals.notManufacturer

/**
 * Ban the genymotion emulator from running
 */
fun OSInformationQuery.banGenymotionEmulator() =
    conditionalBuilder {
        with(bannedBoard(OsCheckConstants.UNKNOWN))
        and(notManufacturer(OsCheckConstants.GENYMOTION_MANUFACTURER))
        and(bannedBootloader(OsCheckConstants.UNKNOWN))
    }

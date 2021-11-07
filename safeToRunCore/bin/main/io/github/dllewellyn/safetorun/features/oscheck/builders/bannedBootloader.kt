package io.github.dllewellyn.safetorun.features.oscheck.builders

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned bootloader to the list
 *
 * @param bannedBootloader the model to ban
 */
fun OSInformationQuery.bannedBootloader(bannedBootloader: String): Conditional =
    baseOsCheck({ "Banned bootloader ${bootloader()} == $bannedBootloader" }) {
        bootloader() == bannedBootloader
    }

package com.safetorun.features.oscheck.builders

import com.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned bootloader to the list
 *
 * @param bannedBootloader the model to ban
 */
fun OSInformationQuery.bannedBootloader(bannedBootloader: String): Conditional =
    baseOsCheck({ "Banned bootloader ${bootloader()} == $bannedBootloader" }) {
        bootloader() == bannedBootloader
    }

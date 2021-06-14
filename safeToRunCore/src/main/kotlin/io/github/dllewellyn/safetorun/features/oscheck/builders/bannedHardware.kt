package io.github.dllewellyn.safetorun.features.oscheck.builders

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned host
 *
 * @param hardware hardware to ban
 */
inline fun OSInformationQuery.bannedHardware(hardware: String): Conditional =
    baseOsCheck({ "Banned hardware: ${hardware()}" }) {
        hardware == hardware()
    }

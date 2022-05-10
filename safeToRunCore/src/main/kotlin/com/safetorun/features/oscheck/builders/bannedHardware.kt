package com.safetorun.features.oscheck.builders

import com.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned host
 *
 * @param hardware hardware to ban
 */
inline fun OSInformationQuery.bannedHardware(hardware: String): Conditional =
    baseOsCheck({ "Banned hardware: ${hardware()}" }) {
        hardware == hardware()
    }

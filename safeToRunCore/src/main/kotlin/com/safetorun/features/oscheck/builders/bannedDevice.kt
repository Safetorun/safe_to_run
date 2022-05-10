package com.safetorun.features.oscheck.builders

import com.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned device
 *
 * @param device device to ban
 */
fun OSInformationQuery.bannedDevice(device: String): Conditional =
    baseOsCheck({ "Banned device: ${device()}" }) {
        device == device()
    }

package com.safetorun.features.oscheck.builders

import com.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned cpuAbis
 *
 * @param cpuAbis the model to ban
 */
fun OSInformationQuery.bannedCpus(cpuAbis: String): Conditional =
    baseOsCheck({ "Banned CPU $cpuAbis == ${cpuAbi()}" }) {
        cpuAbi().contains(cpuAbis)
    }

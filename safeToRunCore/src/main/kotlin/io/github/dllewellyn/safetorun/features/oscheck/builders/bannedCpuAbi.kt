package io.github.dllewellyn.safetorun.features.oscheck.builders

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned cpuAbis
 *
 * @param cpuAbis the model to ban
 */
fun OSInformationQuery.bannedCpus(cpuAbis: String): Conditional =
    baseOsCheck({ "Banned CPU $cpuAbis == ${cpuAbi()}" }) {
        cpuAbi().contains(cpuAbis)
    }

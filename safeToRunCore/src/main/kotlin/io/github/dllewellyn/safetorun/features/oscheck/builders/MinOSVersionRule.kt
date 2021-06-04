package io.github.dllewellyn.safetorun.features.oscheck.builders

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.baseOsCheck

/**
 * Add a rule to fail if the os version is lower than the min
 *
 * @param minOSVersion
 */
fun OSInformationQuery.minOsVersion(minOSVersion: Int): Conditional =
    baseOsCheck({ "${osVersion()} == $minOSVersion" }) {
        minOSVersion > osVersion()
    }

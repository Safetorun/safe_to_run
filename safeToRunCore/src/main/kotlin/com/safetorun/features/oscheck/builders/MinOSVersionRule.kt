package com.safetorun.features.oscheck.builders

import com.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.baseOsCheck

/**
 * Add a rule to fail if the os version is lower than the min
 *
 * @param minOSVersion
 */
fun OSInformationQuery.minOsVersion(minOSVersion: Int): Conditional =
    baseOsCheck({ "${osVersion()} == $minOSVersion" }) {
        minOSVersion > osVersion()
    }

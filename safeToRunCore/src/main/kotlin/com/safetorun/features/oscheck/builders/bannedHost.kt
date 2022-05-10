package com.safetorun.features.oscheck.builders

import com.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned host
 *
 * @param host the model to ban
 */
fun OSInformationQuery.bannedHost(host: String): Conditional =
    baseOsCheck({ "Banned host $host == ${host()}" }) {
        host == host()
    }

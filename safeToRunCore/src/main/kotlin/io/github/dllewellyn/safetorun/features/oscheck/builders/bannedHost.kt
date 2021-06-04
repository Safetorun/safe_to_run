package io.github.dllewellyn.safetorun.features.oscheck.builders

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned host
 *
 * @param host the model to ban
 */
fun OSInformationQuery.bannedHost(host: String): Conditional =
    baseOsCheck({ "Banned host $host == ${host()}" }) {
        host == host()
    }

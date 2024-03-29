package com.safetorun.features.installorigin

import com.safetorun.checks.SafeToRunCheck

/**
 * Builder for an install origin check. Use [installOrigin] to get an instance
 */
class InstallOriginBuilder internal constructor(
    private val installOriginQuery: InstallOriginQuery,
    private val installOriginStrings: InstallOriginStrings
) {
    private val allowedOrigins = mutableListOf<InstallOrigin>()

    /**
     * Add a valid install origin
     *
     * @param installOrigin a valid install origin
     */
    operator fun plus(installOrigin: InstallOrigin) {
        allowedOrigins.add(installOrigin)
    }

    /**
     * Add install origin using +
     *
     * @receiver Install origin to add
     */
    operator fun InstallOrigin.unaryPlus() {
        plus(this)
    }

    internal fun build(): SafeToRunCheck =
        InstallOriginCheck(installOriginStrings, allowedOrigins, installOriginQuery)
}

/**
 * Create an install origin check
 *
 * @param installOriginQuery the class that can be used to query for install origins
 * @param installOriginStrings the strings to use for errors and successes
 * @param installOriginBuilder a block that is used to build the install origin
 */
fun installOrigin(
    installOriginQuery: InstallOriginQuery,
    installOriginStrings: InstallOriginStrings,
    installOriginBuilder: InstallOriginBuilder.() -> Unit
) = with(InstallOriginBuilder(installOriginQuery, installOriginStrings)) {
    installOriginBuilder()
    build()
}

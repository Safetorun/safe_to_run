package io.github.dllewellyn.safetorun.features.installorigin

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

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

fun installOrigin(
    installOriginQuery: InstallOriginQuery,
    installOriginStrings: InstallOriginStrings,
    installOriginBuilder: InstallOriginBuilder.() -> Unit
) = with(InstallOriginBuilder(installOriginQuery, installOriginStrings)) {
    installOriginBuilder()
    build()
}

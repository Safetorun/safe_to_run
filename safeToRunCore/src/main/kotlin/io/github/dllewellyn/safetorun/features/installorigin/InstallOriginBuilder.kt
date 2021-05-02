package io.github.dllewellyn.safetorun.features.installorigin

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

class InstallOriginBuilder(
    private val installOriginQuery: InstallOriginQuery,
    private val installOriginStrings: InstallOriginStrings
) {
    private val allowedOrigins = mutableListOf<InstallOrigin>()

    operator fun plus(installOrigin: InstallOrigin) {
        allowedOrigins.add(installOrigin)
    }

    operator fun InstallOrigin.unaryPlus() {
        plus(this)
    }

    fun build(): SafeToRunCheck =
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

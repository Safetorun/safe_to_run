package io.github.dllewellyn.safetorun.features.blacklistedapps

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

@DslMarker
annotation class BlacklistedScope

@BlacklistedScope
class BlacklistedAppConfiguration(
    private val blacklistedAppCheck: BlacklistedAppCheck,
    private val appStrings: BlacklistedAppStrings
) {

    private val blacklistedApplications = mutableListOf<String>()

    operator fun String.unaryPlus() = blacklistedApplications.add(this)

    fun build(): SafeToRunCheck =
        BlacklistedApplicationDetection(blacklistedApplications, blacklistedAppCheck, appStrings)
}

package io.github.dllewellyn.safetorun.features.blacklistedapps

@DslMarker
annotation class BlacklistedScope

@BlacklistedScope
class BlacklistedAppConfiguration(
    private val blacklistedAppCheck: BlacklistedAppCheck,
    private val appStrings: BlacklistedAppStrings
) {

    private val blacklistedApplications = mutableListOf<String>()

    operator fun String.unaryPlus() = blacklistedApplications.add(this)

    fun build() = BlacklistedApplicationDetection(blacklistedApplications, blacklistedAppCheck, appStrings)
}



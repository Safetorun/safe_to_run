package com.safetorun.features.blacklistedapps

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

/**
 * Configure applications which you want to avoid running on the same
 * device as your app. Use [blacklistedAppConfiguration] to configure
 */
class BlacklistedAppConfiguration internal constructor(
    private val blacklistedAppCheck: BlacklistedAppCheck,
    private val appStrings: BlacklistedAppStrings
) {

    private val blacklistedApplications = mutableListOf<String>()

    /**
     * Add a blacklisted application
     *
     * @receiver the package name of the application to blacklist
     */
    operator fun String.unaryPlus() = blacklistedApplications.add(this)

    internal fun build(): SafeToRunCheck =
        BlacklistedApplicationDetection(blacklistedApplications, blacklistedAppCheck, appStrings)
}

/**
 * Create a blacklisted app configuration
 *
 * @param blacklistedAppCheck the app check to use
 * @param appStrings the app strings to use
 * @param block the block to run for configuration
 */
fun blacklistedAppConfiguration(
    blacklistedAppCheck: BlacklistedAppCheck,
    appStrings: BlacklistedAppStrings,
    block: BlacklistedAppConfiguration.() -> Unit
): SafeToRunCheck {
    return with(BlacklistedAppConfiguration(blacklistedAppCheck, appStrings)) {
        block()
        build()
    }
}

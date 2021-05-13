package io.github.dllewellyn.safetorun.features.blacklistedapps

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

fun blacklistConfig(
    appCheck: BlacklistedAppCheck,
    appStrings: BlacklistedAppStrings,
    block: BlacklistedAppConfiguration.() -> Unit
): SafeToRunCheck =
    with(BlacklistedAppConfiguration(appCheck, appStrings)) {
        block()
        build()
    }

package com.safetorun.features.blacklistedapps

import com.safetorun.checks.SafeToRunCheck

/**
 * Create a blacklisted config
 *
 * @param appCheck the blacklisted app check
 * @param appStrings the strings to use for errors and successes
 * @param block the code to run to configure this check
 *
 * @return a safe to run check that can be run with `isSafeToRun()`
 */
fun blacklistConfig(
    appCheck: BlacklistedAppCheck,
    appStrings: BlacklistedAppStrings,
    block: BlacklistedAppConfiguration.() -> Unit
): SafeToRunCheck =
    with(BlacklistedAppConfiguration(appCheck, appStrings)) {
        block()
        build()
    }

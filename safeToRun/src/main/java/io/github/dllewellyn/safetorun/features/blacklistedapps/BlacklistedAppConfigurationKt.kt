package io.github.dllewellyn.safetorun.features.blacklistedapps

import android.content.Context


/**
 * Configure application blacklisting by specifying an application which you want
 * to warn about
 *
 * ```
 * this errorIf blacklistConfiguration {
 *  +"com.abc.def"
 *  +"com.google.earth"
 * }
 * ```
 */
fun Context.blacklistConfiguration(block: BlacklistedAppConfiguration.() -> Unit) =
    with(
        BlacklistedAppConfiguration(
            AndroidBlacklistedAppCheck(this),
            AndroidBlacklistedAppStrings(this@blacklistConfiguration)
        )
    ) {
        block()
        build()
    }
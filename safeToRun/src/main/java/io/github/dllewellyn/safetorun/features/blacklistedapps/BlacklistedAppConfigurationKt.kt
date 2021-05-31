package io.github.dllewellyn.safetorun.features.blacklistedapps

import android.content.Context

/**
 * Configure application blacklisting by specifying an application which you want
 * to warn about
 *
 * ```
 * blacklistConfiguration {
 *  +"com.abc.def"
 *  +"com.google.earth"
 * }.error()
 * ```
 *
 * @param block builder for configuration
 */
fun Context.blacklistConfiguration(block: BlacklistedAppConfiguration.() -> Unit) =
    blacklistedAppConfiguration(
        AndroidBlacklistedAppCheck(this),
        AndroidBlacklistedAppStrings(this@blacklistConfiguration),
        block
    )

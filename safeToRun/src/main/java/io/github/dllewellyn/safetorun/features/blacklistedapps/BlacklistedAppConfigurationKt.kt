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
        { containsPackage(it) },
        AndroidBlacklistedAppStrings(this@blacklistConfiguration),
        block
    )

/**
 * Configure application blacklisting by specifying an application(s) which you want
 * to warn about
 *
 * Example
 * ```safeToRun(
 *      buildSafeToRunCheckList {
 *          add {
 *              blacklistConfigurationRule(packageName)
 *          }
 *      }
 * )```
 *
 * @return has the rule been breaches
 */
inline fun Context.blacklistConfigurationRule(vararg blacklistedApp: String): Boolean =
    blacklistedApp.isNotEmpty() && blacklistedApp.toList()
        .any { containsPackage(it) }

/**
 * Check if the specific package exists on the device
 *
 * @param packageName the package we're looking for
 */
inline fun Context.containsPackage(packageName: String): Boolean =
    packageManager.getInstalledPackages(0)
        .map { it.packageName }
        .contains(packageName)

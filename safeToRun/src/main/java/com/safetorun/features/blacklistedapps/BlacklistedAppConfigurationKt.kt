package com.safetorun.features.blacklistedapps

import android.annotation.SuppressLint
import android.content.Context

/**
 */
/**
 * Configure application blacklisting by specifying an application(s) which you want
 * to warn about
 *
 * Example
 * ```safeToRun(
 *      blacklistedAppCheck("Test app", "Test app 2")
 * )```
 *
 * @return has the rule been breaches
 */
inline fun Context.blacklistedAppCheck(vararg blacklistedApp: String): Boolean =
    blacklistedApp.isNotEmpty() && blacklistedApp.toList()
        .any { containsPackage(it) }

/**
 * Check if the specific package exists on the device
 *
 * @param packageName the package we're looking for
 */
@SuppressLint("QueryPermissionsNeeded")
inline fun Context.containsPackage(packageName: String): Boolean =
    packageManager.getInstalledPackages(0)
        .map { it.packageName }
        .contains(packageName)

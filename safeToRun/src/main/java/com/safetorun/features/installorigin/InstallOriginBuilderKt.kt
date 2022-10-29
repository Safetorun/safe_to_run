package com.safetorun.features.installorigin

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build

/**
 * Install origin for the google play store
 */
const val PLAY_STORE = "com.android.vending"

/**
 * Install origin for the amazon app store
 */
const val AMAZON_STORE = "com.amazon.venezia"

/**
 * Get the installer that installed this application
 *
 * @param versionNumber the version number that we're running on
 */
@SuppressLint("NewApi")
fun Context.getInstaller(versionNumber: Int = Build.VERSION.SDK_INT): String? {
    return if (versionNumber >= Build.VERSION_CODES.R) {
        packageManager.getInstallSourceInfo(packageName).installingPackageName
    } else {
        packageManager.getInstallerPackageName(packageName)
    }
}

/**
 * Install origin check without the default origins (e.g. play store and amazon app store)
 *
 * @param allowedOrigins a list of the allowed origins
 */
inline fun Context.installOriginCheckWithoutDefaultsCheck(vararg allowedOrigins: String): Boolean {
    return allowedOrigins.toList().contains(getInstaller()).not()
}

/**
 * Install origin check with the default origins (e.g. play store and amazon app store)
 *
 * @param allowedOrigins a list of the allowed origins
 */
inline fun Context.installOriginCheckWithDefaultsCheck(vararg allowedOrigins: String): Boolean {
    return installOriginCheckWithoutDefaultsCheck(
        PLAY_STORE,
        AMAZON_STORE,
        *allowedOrigins
    )
}

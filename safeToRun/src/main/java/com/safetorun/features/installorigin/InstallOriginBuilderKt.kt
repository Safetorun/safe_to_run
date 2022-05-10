package com.safetorun.features.installorigin

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

/**
 * Retrieve a safe to run check which will verify that the app was installed
 * by either google or amazon (plus any parameters passed in)
 *
 * @param allowedOrigins a list of places that it is permissible for your app to
 * have been installed from
 */
fun Context.installOriginCheckWithDefaults(vararg allowedOrigins: String): SafeToRunCheck {
    return installOriginCheckWithoutDefaults(
        GooglePlayStore().originPackage,
        AmazonStore().originPackage,
        *allowedOrigins
    )
}

/**
 * Retrieve a safe to run check which will verify that the app was installed
 * by either google or amazon (plus any parameters passed in)
 *
 * @param allowedOrigins a list of places that it is permissible for your app to
 * have been installed from
 */
fun Context.installOriginCheckWithoutDefaults(vararg allowedOrigins: String): SafeToRunCheck {
    return installOrigin(
        ::getInstaller,
        AndroidInstallOriginStrings(this)
    ) {
        allowedOrigins
            .map(::InstallOrigin)
            .forEach(::plus)
    }
}

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
        GooglePlayStore().originPackage,
        AmazonStore().originPackage,
        *allowedOrigins
    )
}

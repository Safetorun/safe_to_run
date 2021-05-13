package io.github.dllewellyn.safetorun.features.installorigin

import android.content.Context
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
    return installOrigin(AndroidInstallOriginQuery(this), AndroidInstallOriginStrings(this)) {
        allowedOrigins
            .map(::InstallOrigin)
            .forEach(::plus)
    }
}

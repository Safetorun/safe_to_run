package io.github.dllewellyn.safetorun.features.installorigin

import android.content.Context
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

internal class InstallOriginBuilder(private val context: Context) {
    private val allowedOrigins = mutableListOf<InstallOrigin>()

    operator fun plus(installOrigin: InstallOrigin) {
        allowedOrigins.add(installOrigin)
    }

    operator fun InstallOrigin.unaryPlus() {
        plus(this)
    }

    fun build(): SafeToRunCheck =
        InstallOriginCheck(context, AndroidInstallOriginStrings(context), allowedOrigins)
}


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
    return with(InstallOriginBuilder(this)) {
        allowedOrigins
            .map(::InstallOrigin)
            .forEach(::plus)
        build()
    }
}
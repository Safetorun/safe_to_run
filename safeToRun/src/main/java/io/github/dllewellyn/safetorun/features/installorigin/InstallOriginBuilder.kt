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

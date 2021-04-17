package com.andro.safetorun.features.blacklistedapps

import android.content.Context
import com.andro.safetorun.checks.SafeToRunCheck

@DslMarker
annotation class BlacklistedScope

@BlacklistedScope
class BlacklistedAppConfiguration(private val blacklistedAppCheck: BlacklistedAppCheck) {

    private val blacklistedApplications = mutableListOf<String>()

    operator fun String.unaryPlus() = blacklistedApplications.add(this)

    internal fun build() = BlacklistedApplicationDetection(blacklistedApplications, blacklistedAppCheck)
}


fun blacklistConfiguration(appCheck: BlacklistedAppCheck, block: BlacklistedAppConfiguration.() -> Unit) : SafeToRunCheck=
    with(BlacklistedAppConfiguration(appCheck)) {
        block()
        build()
    }

fun Context.blacklistConfiguration(block : BlacklistedAppConfiguration.() -> Unit) =
    blacklistConfiguration(DefaultBlacklistedAppCheck(this), block)
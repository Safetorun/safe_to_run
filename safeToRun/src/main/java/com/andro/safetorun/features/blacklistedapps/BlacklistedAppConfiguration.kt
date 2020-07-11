package com.andro.safetorun.features.blacklistedapps

import android.content.Context

@DslMarker
annotation class BlacklistedScope

@BlacklistedScope
class BlacklistedAppConfiguration(private val blacklistedAppCheck: BlacklistedAppCheck) {

    private val blacklistedApplications = mutableListOf<String>()

    operator fun String.unaryPlus() = blacklistedApplications.add(this)

    fun build() = BlacklistedApplicationDetection(blacklistedApplications, blacklistedAppCheck)
}


fun blacklistConfiguration(appCheck: BlacklistedAppCheck, block: BlacklistedAppConfiguration.() -> Unit) =
    with(BlacklistedAppConfiguration(appCheck)) {
        block()
        build()
    }

fun Context.blacklistConfiguration(block : BlacklistedAppConfiguration.() -> Unit) =
    blacklistConfiguration(DefaultBlacklistedAppCheck(this), block)
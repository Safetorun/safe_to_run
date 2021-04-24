package io.github.dllewellyn.safetorun.features.blacklistedapps

import android.content.Context
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

@DslMarker
annotation class BlacklistedScope

@BlacklistedScope
class BlacklistedAppConfiguration(private val blacklistedAppCheck: BlacklistedAppCheck) {

    private val blacklistedApplications = mutableListOf<String>()
    internal lateinit var appStrings: BlacklistedAppStrings

    operator fun String.unaryPlus() = blacklistedApplications.add(this)

    internal fun build() = BlacklistedApplicationDetection(blacklistedApplications, blacklistedAppCheck, appStrings)
}


internal fun blacklistConfiguration(
    appCheck: BlacklistedAppCheck,
    appStrings: BlacklistedAppStrings,
    block: BlacklistedAppConfiguration.() -> Unit
): SafeToRunCheck =
    with(BlacklistedAppConfiguration(appCheck)) {
        this.appStrings = appStrings
        block()
        build()
    }

fun Context.blacklistConfiguration(block: BlacklistedAppConfiguration.() -> Unit) =
    blacklistConfiguration(
        DefaultBlacklistedAppCheck(this),
        AndroidBlacklistedAppStrings(this@blacklistConfiguration),
        block
    )
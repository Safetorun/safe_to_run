package io.github.dllewellyn.safetorun.features.blacklistedapps

import android.content.Context
import android.content.res.Resources
import io.github.dllewellyn.safetorun.R

internal class AndroidBlacklistedAppStrings(private val context : Context) : BlacklistedAppStrings {

    private val resources : Resources
        get() = context.resources

    override fun foundBlacklistedAppMessage(blacklistedApp: String): String {
        return resources.getString(R.string.found_blacklisted_app, blacklistedApp)
    }

    override fun didNotFindBlacklistedAppMessage(): String {
        return resources.getString(R.string.no_blacklisted_apps_found)
    }
}
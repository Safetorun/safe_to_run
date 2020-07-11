package com.andro.safetorun.features.blacklistedapps

import android.content.Context

class DefaultBlacklistedAppCheck(private val context: Context) : BlacklistedAppCheck {

    override fun isAppPresent(packageName: String): Boolean {
        return context.packageManager.getInstalledApplications(0)
            .map { it.packageName }
            .contains(packageName)
    }
}


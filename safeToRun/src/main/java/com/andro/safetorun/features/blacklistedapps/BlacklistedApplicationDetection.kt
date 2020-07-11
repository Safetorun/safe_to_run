package com.andro.safetorun.features.blacklistedapps

import android.content.Context
import com.andro.safetorun.checks.SafeToRunCheck

class BlacklistedApplicationDetection(
    private val allFiles: List<String>,
    private val blacklistedAppCheck: BlacklistedAppCheck
) : SafeToRunCheck {

    override fun canRun(context: Context): Boolean {
        return allFiles.any {
            blacklistedAppCheck.isAppPresent(it)
        }.not()
    }
}


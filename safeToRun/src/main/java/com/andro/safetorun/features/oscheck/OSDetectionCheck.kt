package com.andro.safetorun.features.oscheck

import android.content.Context
import com.andro.safetorun.checks.SafeToRunCheck

class OSDetectionCheck(
    private val osDetectionConfig: OSDetectionConfig
) : SafeToRunCheck {

    override fun canRun(context: Context): Boolean {
        osDetectionConfig.bannedOsResult.forEach { conditional ->
            if (!conditional()) {
                return false
            }
        }

        return true
    }
}
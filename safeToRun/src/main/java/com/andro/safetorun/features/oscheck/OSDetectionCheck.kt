package com.andro.safetorun.features.oscheck

import android.content.Context
import com.andro.safetorun.checks.SafeToRunCheck

class OSDetectionCheck : SafeToRunCheck {

    override fun canRun(context: Context): Boolean {
        return false
    }

}
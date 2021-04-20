package com.andro.safetorun.features.oscheck

import android.content.Context
import com.andro.safetorun.R
import com.andro.safetorun.reporting.BaseAndroidStrings

internal class AndroidOSDetectionStrings(context: Context) : BaseAndroidStrings(context),
    OSDetectionStrings {

    override fun genericFailureMessage(): String {
        return resources.getString(R.string.os_check_failed)
    }

    override fun genericPassMessage(): String {
        return resources.getString(R.string.os_check_passed)
    }
}
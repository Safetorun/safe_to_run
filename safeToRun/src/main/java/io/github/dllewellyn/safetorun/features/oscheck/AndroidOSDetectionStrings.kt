package io.github.dllewellyn.safetorun.features.oscheck

import android.content.Context
import io.github.dllewellyn.safetorun.R
import io.github.dllewellyn.safetorun.reporting.BaseAndroidStrings

internal class AndroidOSDetectionStrings(context: Context) : BaseAndroidStrings(context),
    OSDetectionStrings {

    override fun genericFailureMessage(): String {
        return resources.getString(R.string.os_check_failed)
    }

    override fun genericPassMessage(): String {
        return resources.getString(R.string.os_check_passed)
    }
}

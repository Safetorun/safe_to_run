package com.safetorun.features.rootdetection

import android.content.Context
import com.safetorun.R
import com.safetorun.reporting.BaseAndroidStrings

internal class AndroidRootDetectionStrings(context: Context) : BaseAndroidStrings(context), RootDetectionStrings {
    override fun rootDetectionFailedMessage(): String {
        return resources.getString(R.string.root_detection_failed)
    }

    override fun rootDetectionPassedMessage(): String {
        return resources.getString(R.string.root_detection_passed)
    }

    override fun rootDetectionDidNotRun(): String {
        return resources.getString(R.string.root_detection_did_not_run)
    }
}

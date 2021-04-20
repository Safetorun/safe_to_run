package com.andro.safetorun.features.rootdetection

import android.content.Context
import com.andro.safetorun.checks.SafeToRunCheck

class RootDetectionConfig {
    var tolerateRoot = false
    var tolerateBusyBox = true
}

fun Context.rootDetection(config: RootDetectionConfig.() -> Unit): SafeToRunCheck =
    RootDetectionConfig().apply {
        config()
    }.run {
        RootBeerRootDetection(
            this,
            AndroidRootDetectionChecker(this@rootDetection),
            AndroidRootDetectionStrings(this@rootDetection)
        )
    }

package com.andro.safetorun.features.rootdetection

import com.andro.safetorun.checks.SafeToRunCheck

class RootDetectionConfig {
    var tolerateRoot = false
    var tolerateBusyBox = true
}

fun rootDetection(config: RootDetectionConfig.() -> Unit): SafeToRunCheck =
    RootDetectionConfig().apply {
        config()
    }.run {
        RootBeerRootDetection(this)
    }

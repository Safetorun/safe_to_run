package io.github.dllewellyn.safetorun.features.rootdetection

import android.content.Context
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

/**
 * Configuration for root detection.
 */
class RootDetectionConfig {
    @Deprecated("To tolerate root, use .warn() instead of .error()")
    var tolerateRoot = false

    /**
     * Tolerate if busybox is on device. Some devices have busybox without being rooted
     */
    var tolerateBusyBox = true
}

/**
 * Configure root detection
 *
 * @param config root detection configuration
 *
 * @receiver Android context
 */
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
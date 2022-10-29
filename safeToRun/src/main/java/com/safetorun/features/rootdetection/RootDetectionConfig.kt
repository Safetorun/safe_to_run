package com.safetorun.features.rootdetection

import android.content.Context
import com.scottyab.rootbeer.RootBeer

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
 * Check for root (defers to rootbeer)
 *
 * @param tolerateBusyBox whether or not to tolerate busybox
 */
inline fun Context.rootDetectionCheck(tolerateBusyBox: Boolean = true): Boolean {
    return RootBeer(this).run {
        if (tolerateBusyBox) {
            isRooted
        } else {
            isRootedWithBusyBoxCheck
        }
    }
}

package com.safetorun.features.rootdetection

import android.content.Context
import com.scottyab.rootbeer.RootBeer

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

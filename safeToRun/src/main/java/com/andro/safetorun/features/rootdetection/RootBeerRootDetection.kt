package com.andro.safetorun.features.rootdetection

import android.content.Context
import com.andro.safetorun.checks.SafeToRunCheck
import com.scottyab.rootbeer.RootBeer

class RootBeerRootDetection(private val rootDetectionConfig: RootDetectionConfig) :
    SafeToRunCheck {

    override fun canRun(context: Context): Boolean {
        if (rootDetectionConfig.tolerateRoot) return true

        return if (rootDetectionConfig.tolerateBusyBox) {
            RootBeer(context).isRootedWithBusyBoxCheck.not()
        } else {
            RootBeer(context).isRooted.not()
        }
    }

}

package com.safetorun.features.rootdetection

import android.content.Context
import com.scottyab.rootbeer.RootBeer

internal class AndroidRootDetectionChecker(private val context: Context) : RootDetectionChecker {

    override fun isRooted(): Boolean {
        return RootBeer(context).isRooted
    }

    override fun isRootedWithBusyBoxCheck(): Boolean {
        return RootBeer(context).isRootedWithBusyBoxCheck
    }
}

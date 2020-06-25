package com.andro.safetorun.features.rootdetection

import android.content.Context
import com.scottyab.rootbeer.RootBeer

class RootBeerRootDetection(private val context: Context) : RootDetection {

    private val rootBeer by lazy {
        RootBeer(context)
    }

    override fun isRooted(): Boolean = rootBeer.isRootedWithBusyBoxCheck

    override fun isRootedIgnoringBusyBox(): Boolean = rootBeer.isRooted

}
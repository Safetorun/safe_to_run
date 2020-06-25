package com.andro.safetorun.di

import android.content.Context
import com.andro.safetorun.features.rootdetection.RootBeerRootDetection
import com.andro.safetorun.features.rootdetection.RootDetection

interface SafeToRunFactory {
    fun rootDetection(context: Context): RootDetection
}

object SafeToRunDefault : SafeToRunFactory {

    override fun rootDetection(context: Context): RootDetection {
        return RootBeerRootDetection(context)
    }

}


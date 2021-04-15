package com.andro.safetorun.features.oscheck

import android.os.Build
import com.andro.safetorun.conditional.Conditional

object OSConfiguration {

    private val osConfiguration = object : OSInformationQuery {
        override fun osVersion(): Int = Build.VERSION.SDK_INT
        override fun manufacturer(): String = Build.MANUFACTURER
        override fun model(): String = Build.MODEL
    }

    fun minOsVersion(minOSVersion: Int): Conditional {
        return osConfiguration.minOsVersion(minOSVersion)
    }

    fun notManufacturer(manufacturerName: String): Conditional {
        return osConfiguration.notManufacturer(manufacturerName)
    }
}
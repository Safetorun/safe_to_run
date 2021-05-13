package io.github.dllewellyn.safetorun.features.oscheck

import android.os.Build
import io.github.dllewellyn.safetorun.conditional.Conditional

object OSConfiguration {

    private val osInformationQuery = object : OSInformationQuery {
        override fun osVersion(): Int = Build.VERSION.SDK_INT
        override fun manufacturer(): String = Build.MANUFACTURER
        override fun model(): String = Build.MODEL
    }

    fun minOsVersion(minOSVersion: Int): Conditional {
        return osInformationQuery.minOsVersion(minOSVersion)
    }

    fun notManufacturer(manufacturerName: String): Conditional {
        return osInformationQuery.notManufacturer(manufacturerName)
    }
}

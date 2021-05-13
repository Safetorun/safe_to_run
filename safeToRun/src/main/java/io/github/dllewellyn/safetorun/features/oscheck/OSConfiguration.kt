package io.github.dllewellyn.safetorun.features.oscheck

import android.os.Build
import io.github.dllewellyn.safetorun.conditional.Conditional

object OSConfiguration {

    private val osInformationQuery = object : OSInformationQuery {
        override fun osVersion(): Int = Build.VERSION.SDK_INT
        override fun manufacturer(): String = Build.MANUFACTURER
        override fun model(): String = Build.MODEL
    }

    /**
     * Configure a min os version to fail if we fall below that version
     *
     * @param minOSVersion minimum os version
     */
    fun minOsVersion(minOSVersion: Int): Conditional {
        return osInformationQuery.minOsVersion(minOSVersion)
    }

    /**
     * Configure to disallow a manufacturer - i.e. fail if it's this
     * manufacturer specified
     *
     * @param manufacturerName name of the manufacturer
     */
    fun notManufacturer(manufacturerName: String): Conditional {
        return osInformationQuery.notManufacturer(manufacturerName)
    }
}

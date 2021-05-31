package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional

/**
 * Used to wrap an os configuration with the default OS Information query for the application
 */
object OSConfiguration {

    internal var osInformationQuery: OSInformationQuery = OSInformationQueryAndroid()

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

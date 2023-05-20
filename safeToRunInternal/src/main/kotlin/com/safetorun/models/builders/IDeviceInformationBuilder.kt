package com.safetorun.models.builders

/**
 * Builder for device information
 */
interface IDeviceInformationBuilder {
    /**
     * Add installed application
     */
    fun installedApplication(packageName: String)

    /**
     * Add install origin
     */
    fun installOrigin(installOrigin: String)

    /**
     * Add signature
     */
    fun signature(signature: String)

    /**
     * Add if device is rooted
     */
    fun isRooted(isRooted: Boolean)
}

package com.safetorun.models.builders

import com.safetorun.models.core.DeviceInformation

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
}

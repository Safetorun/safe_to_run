package com.safetorun.models.builders

import com.safetorun.models.core.OsCheck
import com.safetorun.models.models.OsCheckDto

internal interface IOsInformationDtoBuilder : IOsHardwareInformationBuilder {
    /**
     * Add host
     */
    fun host(host: String): OsInformationDtoBuilder

    /**
     * Add bootloader
     */
    fun bootloader(bootloader: String): OsInformationDtoBuilder

    /**
     * Add os version
     */
    fun osVersion(osVersion: String)

    /**
     * Add manufacturer
     */
    fun manufacturer(manufacturer: String)

    /**
     * Add model
     */
    fun model(model: String)

    /**
     * Add a device
     */
    fun device(device: String)

    /**
     * Build the OS Check
     */
    fun buildOsCheck(): OsCheck
}

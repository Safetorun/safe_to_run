package io.github.dllewellyn.safetorun.models.models

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
    fun buildOsCheck(): OsCheckDto
    fun buildPartialOsCheck(): OsCheckDto
}

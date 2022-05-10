package com.safetorun.features.oscheck

/**
 * Query for OS information
 */
interface OSInformationQuery {
    /**
     * OS SDK Version (e.g. 30)
     */
    fun osVersion(): Int

    /**
     * Device manufacturer
     */
    fun manufacturer(): String

    /**
     * Device model
     */
    fun model(): String

    /**
     * Board that the device uses
     */
    fun board(): String

    /**
     * Bootloader information
     */
    fun bootloader(): String

    /**
     * List of CPU ABIs
     */
    fun cpuAbi(): List<String>

    /**
     * Device host
     */
    fun host(): String

    /**
     * Device hardware
     */
    fun hardware(): String

    /**
     * Device from Build.DEVICE
     */
    fun device(): String
}

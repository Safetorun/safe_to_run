package com.safetorun.plus.queries

import android.os.Build
import com.safetorun.features.oscheck.OSInformationQuery

/**
 * OSInformationQueryAndroid is a concrete implementation of the OSInformationQuery interface.
 * It uses Android's Build class to query various details about the device's operating system.
 */
class OSInformationQueryAndroid : OSInformationQuery {

    /**
     * Returns the SDK version of the current Android operating system.
     */
    override fun osVersion(): Int = Build.VERSION.SDK_INT

    /**
     * Returns the manufacturer of the device.
     */
    override fun manufacturer(): String = Build.MANUFACTURER

    /**
     * Returns the model of the device.
     */
    override fun model(): String = Build.MODEL

    /**
     * Returns the board of the device.
     */
    override fun board(): String = Build.BOARD

    /**
     * Returns the bootloader version of the device.
     */
    override fun bootloader(): String = Build.BOOTLOADER

    /**
     * Returns a list of supported ABI (Application Binary Interface).
     * If the SDK version is lower than LOLLIPOP, returns an empty list.
     */
    override fun cpuAbi(): List<String> =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Build.SUPPORTED_ABIS.toList()
        } else {
            emptyList()
        }

    /**
     * Returns the hostname of the device.
     */
    override fun host(): String = Build.HOST

    /**
     * Returns the hardware information of the device.
     */
    override fun hardware(): String = Build.HARDWARE

    /**
     * Returns the device identifier.
     */
    override fun device(): String = Build.DEVICE
}

/**
 * Creates and returns an instance of OSInformationQueryAndroid.
 * This function serves as a factory for creating OSInformationQueryAndroid instances.
 */
inline fun osInformationQuery(): OSInformationQuery = OSInformationQueryAndroid()

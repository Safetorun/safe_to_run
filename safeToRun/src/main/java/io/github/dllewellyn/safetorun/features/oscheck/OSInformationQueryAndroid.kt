package io.github.dllewellyn.safetorun.features.oscheck

import android.os.Build

internal class OSInformationQueryAndroid : OSInformationQuery {
    override fun osVersion(): Int = Build.VERSION.SDK_INT
    override fun manufacturer(): String = Build.MANUFACTURER
    override fun model(): String = Build.MODEL
    override fun board(): String = Build.BOARD
    override fun bootloader(): String = Build.BOOTLOADER
    override fun cpuAbi(): List<String> = Build.SUPPORTED_ABIS.toList()
    override fun host(): String = Build.HOST
    override fun hardware(): String = Build.HARDWARE
    override fun device(): String = Build.DEVICE
}

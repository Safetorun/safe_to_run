package io.github.dllewellyn.safetorun.offdevice

import io.github.dllewellyn.safetorun.models.models.DeviceInformationDtoBuilder

fun interface OffDeviceResultBuilder {
    fun buildOffDeviceResultBuilder(
        deviceSignatureDto: DeviceInformationDtoBuilder
    ): DeviceInformationDtoBuilder
}

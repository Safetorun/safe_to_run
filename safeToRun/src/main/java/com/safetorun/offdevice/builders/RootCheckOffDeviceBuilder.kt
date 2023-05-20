package com.safetorun.offdevice.builders

import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.offdevice.OffDeviceResultBuilder

internal class RootCheckOffDeviceBuilder(private val rootCheck: () -> Boolean) :
    OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(deviceSignatureDto: DeviceInformationDtoBuilder):
            DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            isRooted(rootCheck.invoke())
        }
    }
}

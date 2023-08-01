package com.safetorun.plus.offdevice.builders

import com.safetorun.plus.builders.DeviceInformationDtoBuilder
import com.safetorun.plus.offdevice.OffDeviceResultBuilder

internal class RootCheckOffDeviceBuilder(private val rootCheck: () -> Boolean) :
    OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(deviceSignatureDto: DeviceInformationDtoBuilder):
            DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            isRooted(rootCheck.invoke())
        }
    }
}

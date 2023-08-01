package com.safetorun.plus.offdevice.builders

import com.safetorun.plus.builders.DeviceInformationDtoBuilder
import com.safetorun.plus.offdevice.OffDeviceResultBuilder

internal class CompositeBuilder(private val builders: List<OffDeviceResultBuilder>) :
    OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(deviceSignatureDto: DeviceInformationDtoBuilder):
            DeviceInformationDtoBuilder {
        var dto = deviceSignatureDto
        builders.forEach {
            dto = it.buildOffDeviceResultBuilder(dto)
        }
        return dto
    }
}

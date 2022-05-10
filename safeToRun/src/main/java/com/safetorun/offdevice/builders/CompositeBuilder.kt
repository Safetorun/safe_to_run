package com.safetorun.offdevice.builders

import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.offdevice.OffDeviceResultBuilder

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

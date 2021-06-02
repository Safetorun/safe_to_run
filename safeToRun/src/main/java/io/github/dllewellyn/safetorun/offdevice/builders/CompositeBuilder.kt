package io.github.dllewellyn.safetorun.offdevice.builders

import io.github.dllewellyn.safetorun.models.builders.DeviceInformationDtoBuilder
import io.github.dllewellyn.safetorun.offdevice.OffDeviceResultBuilder

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

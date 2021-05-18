package io.github.dllewellyn.safetorun.offdevice.builders

import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDtoBuilder
import io.github.dllewellyn.safetorun.offdevice.OffDeviceResultBuilder

internal class OSCheckOffDeviceBuilder(private val osInformationQuery: OSInformationQuery) : OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(
        deviceSignatureDto: DeviceInformationDtoBuilder
    ): DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            osVersion(osInformationQuery.osVersion().toString())
            manufacturer(osInformationQuery.manufacturer())
            model(osInformationQuery.model())
        }
    }
}

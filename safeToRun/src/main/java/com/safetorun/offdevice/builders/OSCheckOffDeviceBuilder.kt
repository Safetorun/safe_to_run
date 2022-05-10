package com.safetorun.offdevice.builders

import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.offdevice.OffDeviceResultBuilder

internal class OSCheckOffDeviceBuilder(private val osInformationQuery: OSInformationQuery) : OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(
        deviceSignatureDto: DeviceInformationDtoBuilder
    ): DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            osVersion(osInformationQuery.osVersion().toString())
            manufacturer(osInformationQuery.manufacturer())
            model(osInformationQuery.model())
            bootloader(osInformationQuery.bootloader())
            device(osInformationQuery.device())
            osInformationQuery.cpuAbi().forEach(::cpuAbi)
            hardware(osInformationQuery.hardware())
            board(osInformationQuery.board())
            host(osInformationQuery.host())
        }
    }
}

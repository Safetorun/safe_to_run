package com.safetorun.plus.offdevice.builders

import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.plus.builders.DeviceInformationDtoBuilder
import com.safetorun.plus.offdevice.OffDeviceResultBuilder

internal class OSCheckOffDeviceBuilder(private val osInformationQuery: OSInformationQuery) :
    OffDeviceResultBuilder {

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

package com.safetorun.plus.offdevice.builders

import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.plus.offdevice.OffDeviceResultBuilder

internal class BlacklistedAppsOffDeviceBuilder(private val installedPackagesQuery: () -> List<String>) :
    OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(deviceSignatureDto: DeviceInformationDtoBuilder):
            DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            installedPackagesQuery()
                .forEach(::installedApplication)
        }
    }
}

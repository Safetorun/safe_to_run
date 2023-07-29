package com.safetorun.plus.offdevice.builders

import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.plus.offdevice.OffDeviceResultBuilder
import com.safetorun.plus.queries.InstalledPackagesQuery

internal class BlacklistedAppsOffDeviceBuilder(private val installedPackagesQuery: InstalledPackagesQuery) :
    OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(deviceSignatureDto: DeviceInformationDtoBuilder):
            DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            installedPackagesQuery
                .listInstalledPackages()
                .forEach(::installedApplication)
        }
    }
}

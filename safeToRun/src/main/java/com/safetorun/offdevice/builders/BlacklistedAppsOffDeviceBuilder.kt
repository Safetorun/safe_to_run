package com.safetorun.offdevice.builders

import com.safetorun.features.blacklistedapps.InstalledPackagesQuery
import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.offdevice.OffDeviceResultBuilder

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

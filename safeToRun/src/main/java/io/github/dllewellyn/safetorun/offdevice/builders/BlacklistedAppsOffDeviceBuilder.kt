package io.github.dllewellyn.safetorun.offdevice.builders

import io.github.dllewellyn.safetorun.features.blacklistedapps.InstalledPackagesQuery
import io.github.dllewellyn.safetorun.models.builders.DeviceInformationDtoBuilder
import io.github.dllewellyn.safetorun.offdevice.OffDeviceResultBuilder

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

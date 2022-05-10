package com.safetorun.offdevice.builders

import com.safetorun.features.installorigin.InstallOriginQuery
import com.safetorun.models.builders.DeviceInformationDtoBuilder
import com.safetorun.offdevice.OffDeviceResultBuilder

internal class InstallOriginOffDeviceBuilder(private val installOriginQuery: InstallOriginQuery) :
    OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(deviceSignatureDto: DeviceInformationDtoBuilder):
            DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            installOrigin(installOriginQuery.getInstallPackageName())
        }
    }
}

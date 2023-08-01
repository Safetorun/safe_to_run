package com.safetorun.plus.offdevice.builders

import com.safetorun.features.installorigin.InstallOriginQuery
import com.safetorun.plus.builders.DeviceInformationDtoBuilder
import com.safetorun.plus.offdevice.OffDeviceResultBuilder

internal class InstallOriginOffDeviceBuilder(private val installOriginQuery: InstallOriginQuery) :
    OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(deviceSignatureDto: DeviceInformationDtoBuilder):
            DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            installOrigin(installOriginQuery.getInstallPackageName())
        }
    }
}

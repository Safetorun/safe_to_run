package io.github.dllewellyn.safetorun.offdevice.builders

import io.github.dllewellyn.safetorun.features.installorigin.InstallOriginQuery
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDtoBuilder
import io.github.dllewellyn.safetorun.offdevice.OffDeviceResultBuilder

internal class InstallOriginOffDeviceBuilder(private val installOriginQuery: InstallOriginQuery) :
    OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(deviceSignatureDto: DeviceInformationDtoBuilder):
            DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            installOrigin(installOriginQuery.getInstallPackageName())
        }
    }
}

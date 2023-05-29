package com.safetorun.models.builders

import com.safetorun.models.models.BlacklistedAppsDto
import com.safetorun.models.models.DeviceInformationDto
import com.safetorun.models.models.InstallOriginDto
import com.safetorun.models.models.OsCheckDto
import com.safetorun.models.models.SignatureVerificationDto
import java.util.*

/**
 * Device information DTO builder.
 *
 * Builds a Device Information object
 */
class DeviceInformationDtoBuilder internal constructor(
    private val apiKey: String,
    private val deviceInformationBuilder: DeviceInformationBuilder = DeviceInformationBuilder()
) : IDeviceInformationBuilder by deviceInformationBuilder,
    IOsInformationDtoBuilder by deviceInformationBuilder {

    private var _deviceId = UUID.randomUUID().toString()

    /**
     * Add device ID
     */
    fun deviceId(deviceId: String) {
        _deviceId = deviceId
    }

    /**
     * Build a device information DTO
     *
     * @return a device information dto
     */
    fun build() = deviceInformationBuilder.build().run {
        DeviceInformationDto(
            apiKey,
            _deviceId,
            osCheck = osCheck.run {
                OsCheckDto(
                    osVersion,
                    manufacturer,
                    model,
                    board,
                    bootloader,
                    cpuAbi,
                    host,
                    hardware,
                    device
                )
            },
            installOrigin = InstallOriginDto(installOrigin.installOriginPackageName),
            blacklistedApp = BlacklistedAppsDto(blacklistedApp.installedPackages),
            signatureVerification = SignatureVerificationDto(signatureVerification.signature),
            isRooted = isRooted
        )
    }
}

/**
 * Create a device information class
 *
 * @param apiKey apiKey of the backend
 * @param block builder function to run
 */
fun deviceInformationBuilder(
    apiKey: String,
    block: DeviceInformationDtoBuilder.() -> Unit
): DeviceInformationDto {
    return with(DeviceInformationDtoBuilder(apiKey)) {
        block()
        build()
    }
}

/**
 * Create an instance of device information
 *
 * @param apiKey api key
 */
fun deviceInformationBuilder(apiKey: String) = DeviceInformationDtoBuilder(apiKey)

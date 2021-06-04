package io.github.dllewellyn.safetorun.models.builders

import io.github.dllewellyn.safetorun.models.models.BlacklistedAppsDto
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.InstallOriginDto
import io.github.dllewellyn.safetorun.models.models.OsCheckDto
import io.github.dllewellyn.safetorun.models.models.SignatureVerification
import java.util.UUID

/**
 * Device information DTO builder.
 *
 * Builds a Device Information object
 */
class DeviceInformationDtoBuilder internal constructor(
    private val apiKey: String,
    private val osInformation: IOsInformationDtoBuilder = OsInformationDtoBuilder()
) : IOsInformationDtoBuilder by osInformation {

    private var _deviceId = UUID.randomUUID().toString()
    private val _installedApplications: MutableList<String> = mutableListOf()
    private var _signature: String? = null
    private var _installOrigin: String? = null

    /**
     * Add device ID
     */
    fun deviceId(deviceId: String) {
        _deviceId = deviceId
    }

    /**
     * Add installed application
     */
    fun installedApplication(packageName: String) {
        _installedApplications.add(packageName)
    }

    /**
     * Add signature
     */
    fun signature(signature: String?) {
        _signature = signature
    }

    /**
     * Build a full device information DTO. Will pass
     * blank items if anything is not provided
     *
     * @return a device information object
     */
    fun buildPartial(): DeviceInformationDto {
        return buildDtoForUnwrappedValues(
            buildPartialOsCheck(),
            _installOrigin,
            _signature,
        )
    }

    /**
     * Build a full device information DTO. Will exception if
     * there are missing values not added
     *
     *
     * @return a device information object
     * @throws IllegalArgumentException if any items are null
     */
    fun build(): DeviceInformationDto {
        return buildDtoForUnwrappedValues(
            buildOsCheck(),
            _installOrigin,
            _signature
        )
    }

    private fun buildDtoForUnwrappedValues(
        osVersionCheck: OsCheckDto,
        installOrigin: String?,
        signature: String?
    ) =
        DeviceInformationDto().apply {
            this.apiKey = this@DeviceInformationDtoBuilder.apiKey
            deviceId = _deviceId
            osCheck = osVersionCheck
            this.installOrigin = InstallOriginDto().apply {
                this.installOriginPackageName = installOrigin
            }
            blacklistedApp = BlacklistedAppsDto().apply { installedPackages = _installedApplications }
            signatureVerification = SignatureVerification().apply {
                signatureVerificationString = signature
            }
        }

    /**
     * Add install origin
     */
    fun installOrigin(installOrigin: String?) {
        _installOrigin = installOrigin
    }
}

internal fun unwrapOrThrow(field: String?, fieldName: String): String {
    return field ?: throw IllegalArgumentException("$fieldName cannot be null")
}

/**
 * Create a device information class
 *
 * @param apiKey apiKey of the backend
 * @param block builder function to run
 */
fun deviceInformationBuilder(apiKey: String, block: DeviceInformationDtoBuilder.() -> Unit): DeviceInformationDto {
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

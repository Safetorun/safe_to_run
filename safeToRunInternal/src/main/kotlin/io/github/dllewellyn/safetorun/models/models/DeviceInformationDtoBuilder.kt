package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

class DeviceInformationDtoBuilder(private val apiKey: String) {

    private var _deviceId = UUID.randomUUID().toString()
    private var _installOrigin: String? = null
    private var _osVersion: String? = null
    private var _manufacturer: String? = null
    private val _installedApplications: MutableList<String> = mutableListOf()
    private var _signature: String? = null
    private var _model: String? = null

    fun installOrigin(installOrigin: String?) {
        _installOrigin = installOrigin
    }

    fun deviceId(deviceId: String) {
        _deviceId = deviceId
    }

    fun osVersion(osVersion: String) {
        _osVersion = osVersion
    }

    fun manufacturer(manufacturer: String) {
        _manufacturer = manufacturer
    }

    fun installedApplication(packageName: String) {
        _installedApplications.add(packageName)
    }

    fun signature(signature: String?) {
        _signature = signature
    }

    fun model(model: String) {
        _model = model
    }

    fun buildPartial(): DeviceInformationDto {
        val osVersion = _osVersion ?: ""
        val manufacturer = _manufacturer ?: ""
        val model = _model ?: ""

        return buildDtoForUnwrappedValues(osVersion, manufacturer, model, _installOrigin, _signature)
    }

    fun build(): DeviceInformationDto {
        val osVersion = unwrapOrThrow(_osVersion, "Os version")
        val manufacturer = unwrapOrThrow(_manufacturer, "Manufacturer")
        val model = unwrapOrThrow(_model, "Model")

        return buildDtoForUnwrappedValues(
            osVersion,
            manufacturer,
            model,
            _installOrigin,
            _signature
        )
    }

    private fun buildDtoForUnwrappedValues(
        osVersion: String,
        manufacturer: String,
        model: String,
        installOrigin: String?,
        signature: String?
    ) =
        DeviceInformationDto().apply {
            this.apiKey = this@DeviceInformationDtoBuilder.apiKey
            deviceId = _deviceId
            osCheck = OsCheckDto().apply {
                this.osVersion = osVersion
                this.manufacturer = manufacturer
                this.model = model
            }
            this.installOrigin = InstallOriginDto().apply {
                this.installOriginPackageName = installOrigin
            }
            blacklistedApp = BlacklistedAppsDto().apply { installedPackages = _installedApplications }
            signatureVerification = SignatureVerification().apply {
                signatureVerificationString = signature
            }
        }
}

internal fun unwrapOrThrow(field: String?, fieldName: String): String {
    return field ?: throw IllegalArgumentException("$fieldName cannot be null")
}

fun deviceInformation(apiKey: String, block: DeviceInformationDtoBuilder.() -> Unit): DeviceInformationDto {
    return with(DeviceInformationDtoBuilder(apiKey)) {
        block()
        build()
    }
}

fun DeviceInformationDto.serialize() = Json.encodeToString(this)

package io.github.dllewellyn.safetorun.models.models

import java.util.UUID

class DeviceInformationDtoBuilder(private val apiKey: String) {

    private var _deviceId = UUID.randomUUID().toString()
    private var _installOrigin: String? = null
    private var _osVersion: String? = null
    private var _manufacturer: String? = null
    private val _installedApplications: MutableList<String> = mutableListOf()
    private var _signature: String? = null

    fun installOrigin(installOrigin: String) {
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

    fun signature(signature: String) {
        _signature = signature
    }

    fun build(): DeviceInformationDto {

        val osVersion = unwrapOrThrow(_osVersion, "Os version")
        val manufacturer = unwrapOrThrow(_manufacturer, "Manufacturer")
        val installOrigin = unwrapOrThrow(_installOrigin, "Install origin")
        val signature = unwrapOrThrow(_signature, "Signature")

        return DeviceInformationDto().apply {
            this.apiKey = apiKey
            deviceId = _deviceId
            osCheck = OsCheckDto().apply {
                this.osVersion = osVersion
                this.manufacturer = manufacturer
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

    private fun unwrapOrThrow(field: String?, fieldName: String): String {
        return field ?: throw IllegalArgumentException("$fieldName cannot be null")
    }
}

fun deviceInformation(apiKey: String, block: DeviceInformationDtoBuilder.() -> Unit): DeviceInformationDto {
    return with(DeviceInformationDtoBuilder(apiKey)) {
        block()
        build()
    }
}

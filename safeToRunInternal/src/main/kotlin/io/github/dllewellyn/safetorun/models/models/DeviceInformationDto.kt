package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * The DTO used to send data to the server which can be used to
 * perform a device check
 */
data class DeviceInformationDto(
    var apiKey: String = "",
    var deviceId: String = "",
    var osCheck: OsCheckDto = OsCheckDto(),
    var installOrigin: InstallOriginDto = InstallOriginDto(),
    var blacklistedApp: BlacklistedAppsDto = BlacklistedAppsDto(),
    var signatureVerification: SignatureVerification = SignatureVerification(),
)